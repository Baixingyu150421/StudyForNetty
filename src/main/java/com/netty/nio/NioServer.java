package com.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {
    private static Map<String,SocketChannel> clientMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            int selectNumber = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                SocketChannel client;
                if(key.isAcceptable()){
                     ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                     client = serverChannel.accept();
                     client.configureBlocking(false);
                     client.register(selector,SelectionKey.OP_READ);
                     String clientKey = "[" + UUID.randomUUID().toString() + "]" ;
                     clientMap.put(clientKey,client);
                     //keyIterator.remove();
                }else if(key.isReadable()){
                     client = (SocketChannel) key.channel();
                     ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                     int read = client.read(byteBuffer);
                     if(read <0){
                         break;
                     }
                     System.out.println("read bytes: " + read );
                     if(read > 0){
                         //读写切换
                         byteBuffer.flip();
                         Charset charset = Charset.forName("utf-8");
                         System.out.println(client.getRemoteAddress() + " : "+String.valueOf(charset.decode(byteBuffer)));
                         String senderKey = null;
                         for( Map.Entry<String,SocketChannel> entry: clientMap.entrySet() ){
                                 if(client == entry.getValue()){
                                     senderKey = entry.getKey();
                                 }
                         }
                         for(Map.Entry<String,SocketChannel> entry :clientMap.entrySet()){
                             SocketChannel channel = entry.getValue();
                             ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                             writeBuffer.put((senderKey + byteBuffer.array() + "\r\n").getBytes("utf-8"));
                             writeBuffer.flip();
                             channel.write(writeBuffer);
                         }
                     }
                }
            }
            selectionKeys.clear();
        }
    }
}
