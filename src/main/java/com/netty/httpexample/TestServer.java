package com.netty.httpexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.net.URI;

/**
 * netty实现http服务器
 */
public class
TestServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                           // System.out.println("初始化channel");
                            ChannelPipeline pipeline = ch.pipeline();
                            // A combination of {@link HttpRequestDecoder} and {@link HttpResponseEncoder}
                            pipeline.addLast("httpServerCodec",new HttpServerCodec());
                            pipeline.addLast("myServerHandler",new SimpleChannelInboundHandler<HttpObject>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
                                    SocketAddress socketAddress = ctx.channel().remoteAddress();
                                    System.out.println("连接地址："+ socketAddress);
                                    if(msg instanceof HttpRequest){
                                        HttpRequest httpRequest = (HttpRequest)msg;
                                        String name = httpRequest.method().name();
                                        System.out.println("请求的方法名："+ name);
                                        URI uri = new URI(httpRequest.uri());
                                        if("/favicon.ico".equals(uri.getPath())){
                                            System.out.println("请求icon");
                                            return;
                                        }
                                        System.out.println("执行channelRead0");
                                        ByteBuf byteBuf = Unpooled.copiedBuffer("hello netty",CharsetUtil.UTF_8);
                                        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,byteBuf);
                                        response.headers().set(HttpHeaderNames.CONTENT_TYPE ,"text/plain");
                                        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
                                        ctx.writeAndFlush(response);
                                    }
                                    ctx.pipeline().fireChannelRead(msg);
                                }

                                @Override
                                public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("channel注册");
                                    super.channelRegistered(ctx);
                                }

                                @Override
                                public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("channel未注册");
                                    super.channelUnregistered(ctx);
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("channel激活");
                                    super.channelActive(ctx);
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("channel未激活");
                                    super.channelInactive(ctx);
                                }

                                @Override
                                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("添加处理器");
                                    super.handlerAdded(ctx);
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
