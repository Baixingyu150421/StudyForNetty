package com.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * netty实现socket 服务端
 */
public class MyServer {

    public static void main(String[] args) {
        //负责接收请求的事件循环组（本身不处理任何请求，转发给childGroup）
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //真正用于用户处理请求的事件循环组
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //方法链编程返回当前实例
            serverBootstrap.group(parentGroup,childGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.WARN)).childHandler(new MyServerInitializer());
            //准备工作完成，server启动
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
                parentGroup.shutdownGracefully();
                childGroup.shutdownGracefully();
        }
    }

}
