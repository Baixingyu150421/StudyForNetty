package com.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerWebSocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //因为webSocket本身也是构建在http协议之上的   这个处理器是HttpRequestDecoder, HttpResponseEncoder的组合
        pipeline.addLast(new HttpServerCodec());
        //在进行大数据流传输类似于文件传输时netty所提供的状态管理处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //对象聚合  把分段或分块的请求聚合成FullHttpRequest或FullHttpResponse对象
        pipeline.addLast(new HttpObjectAggregator(8192));

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new MyWebSocketHandler());
    }
}
