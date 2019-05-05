package com.netty.firstexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.UUID;

/**
 * 自定义处理器
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        System.out.println("from client" + socketAddress.toString() + msg);
        String  backMsg = "From Server:" + UUID.randomUUID();
        ctx.channel().writeAndFlush(backMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
