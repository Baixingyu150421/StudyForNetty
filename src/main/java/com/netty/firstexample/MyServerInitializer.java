package com.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * 初始化器 childGroup是用来处理（远端）socket请求的 socket/serverSocket是基于TCP/IP协议的
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //管道   理解成这个管道把所有netty提供的处理器或是我们自己实现的处理器串在一起
        ChannelPipeline pipeline = ch.pipeline();
        //在netty中的数据被称作frame 解码  用来解决TCP传输中的半包/拆包/粘包问题
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        //字段长度只允许是1,2,3,4,8
        pipeline.addLast(new LengthFieldPrepender(4));
        //指定解码的字符集
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        //指定编码的字符集
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //自定义socketChannel处理器
        pipeline.addLast(new MyServerHandler());
    }
}
