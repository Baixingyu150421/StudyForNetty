package com.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {
    public static void main(String[] args) throws Exception{
        int [] ports = new int[5];
        ports[0] = 6000;
        ports[1] = 6001;
        ports[2] = 6002;
        ports[3] = 6003;
        ports[4] = 6004;
        Selector selector = Selector.open();
        for (int i = 0 ; i < ports.length ; i++  ){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(inetSocketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口：" + ports[i]);
        }

        while (true){
            int number = selector.select();
            System.out.println("key numbers:" + number);
            //拿到各种类型key的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //迭代集合
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    keyIterator.remove();
                    System.out.println("客户端连接：" + socketChannel.getRemoteAddress());
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readByte = 0;
                    while (true){
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0){
                            break;
                        }
                        byteBuffer.flip();
                        int writeByte = socketChannel.write(byteBuffer);
                        readByte += writeByte;
                        System.out.println("读取： " + readByte + "  from : " + socketChannel.getRemoteAddress());
                        keyIterator.remove();
                    }
                }
            }
        }
    }
}
