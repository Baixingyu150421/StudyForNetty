package com.netty.ExceptionHandlerExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInBoundHandlerB extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //模拟读数据时出现异常
        throw new BusinessException("from ChannelInBoundHandlerB");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(" InBoundHandlerB  exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
