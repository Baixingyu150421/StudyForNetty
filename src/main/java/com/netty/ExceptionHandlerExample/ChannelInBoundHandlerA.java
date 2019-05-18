package com.netty.ExceptionHandlerExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInBoundHandlerA extends ChannelInboundHandlerAdapter {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(" InBoundHandlerA exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
