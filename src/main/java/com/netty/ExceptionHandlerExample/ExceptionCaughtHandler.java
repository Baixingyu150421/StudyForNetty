package com.netty.ExceptionHandlerExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //最终异常都会在这进行处理
        if(cause instanceof BusinessException){
            System.out.println("BusinessException...");
        }
    }
}
