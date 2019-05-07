package com.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String[] args) throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                SocketChannel client = (SocketChannel) key.channel();
                if (client.isConnectionPending()) {
                    client.finishConnect();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.put((client.getLocalAddress() + "连接成功").getBytes());
                    byteBuffer.flip();
                    client.write(byteBuffer);
                    //键盘输入，创建线程池防止主线程阻塞
                    ExecutorService service = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                    service.submit(() -> {
                        while (true) {
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                String readLine = bufferedReader.readLine();
                                ByteBuffer writeByte = ByteBuffer.allocate(1024);
                                writeByte.put(readLine.getBytes());
                                writeByte.flip();
                                client.write(writeByte);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    client.register(selector,SelectionKey.OP_READ);
                }
                //从服务器端读取
                else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer readByte = ByteBuffer.allocate(1024);
                        //读到readByte中
                        int read = channel.read(readByte);
                        if(read > 0){
                            String receiveMessage = new String(readByte.array(),0,read);
                            System.out.println(channel.getRemoteAddress() + ":" + receiveMessage);
                        }
                }
            }
            //清除已经处理完的key
            selectionKeys.clear();
        }
    }
}
