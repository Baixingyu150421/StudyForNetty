package com.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering 散开
 * Gathering  收集
 */
public class NioTest11 {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetAddress = new InetSocketAddress(9999);
        serverSocketChannel.socket().bind(inetAddress);
        int messageLength = 2 + 3 + 4;
        ByteBuffer [] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            int byteRead = 0;
            while (byteRead < messageLength){
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead:" + byteRead);
                Arrays.asList(byteBuffers)
                        .stream().map(buffer -> "position:" + buffer.position() + "limit:" + buffer.limit())
                        .forEach(System.out::println);
            }
            //开始写
            Arrays.asList(byteBuffers).forEach(buffer ->{
                buffer.flip();
            });
            int wirtenLength = 0;
            while(wirtenLength < messageLength){
                long write = socketChannel.write(byteBuffers);
                wirtenLength += write;
            }
            Arrays.asList(byteBuffers).forEach(buffer ->{
                buffer.clear();
            });
            System.out.println("byteRead:  " + byteRead + "wirtenLength:  " + wirtenLength + "messageLength:  " + messageLength);
        }
    }
}
