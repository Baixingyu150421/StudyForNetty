package com.netty.OutBoundHandlerExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

public class MyOutBoundHandlerB extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundB : " + msg);
        ctx.write(msg,promise);
    }
    //用于子类复写这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //向channel写数据，模拟给客户端的响应
        ctx.executor().schedule(()->{
            ctx.channel().write("hello");
        },3, TimeUnit.SECONDS);
    }
}
