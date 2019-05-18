package com.netty.InboundHandlerExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyChannelHandlerC());
        pipeline.addLast(new MyChannelHandlerB());
        pipeline.addLast(new MyChannelHandlerA());
    }
}
