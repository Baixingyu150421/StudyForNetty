package com.netty.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class newClient {
    public static void main(String[] args)throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        Socket socket = socketChannel.socket();
        socketChannel.configureBlocking(true);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8888);
        socket.connect(inetSocketAddress);
        String fileName = "xxxxx";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long currentTimeMillis = System.currentTimeMillis();
        //零拷贝实现
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + transferCount + "耗时：" + (System.currentTimeMillis() - currentTimeMillis) );
        fileChannel.close();
        socketChannel.close();
    }
}
