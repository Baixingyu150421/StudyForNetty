package com.netty.OutBoundHandlerExample;

import com.netty.InboundHandlerExample.MyChannelHandlerB;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyOutBoundHandlerA());
        pipeline.addLast(new MyOutBoundHandlerB());
        pipeline.addLast(new MyOutBoundHandlerC());
    }
}
