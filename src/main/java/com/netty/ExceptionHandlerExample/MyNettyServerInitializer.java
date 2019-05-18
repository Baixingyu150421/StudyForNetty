package com.netty.ExceptionHandlerExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ChannelInBoundHandlerA());
        pipeline.addLast(new ChannelInBoundHandlerB());
        pipeline.addLast(new ChannelInBoundHandlerC());
        pipeline.addLast(new ChannelOutBoundHandlerA());
        pipeline.addLast(new ChannelOutBoundHandlerB());
        pipeline.addLast(new ChannelOutBoundHandlerC());
        pipeline.addLast(new ExceptionCaughtHandler());
    }
}
