package com.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws Exception {
        //text/NioTest2 D:\Netty\text\NioTest2
        FileInputStream fileInputStream = new FileInputStream("text/NioTest2.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(fileInputStream.available());
        FileChannel channel = fileInputStream.getChannel();
        channel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println("字符: " + (char)byteBuffer.get());
        }
        fileInputStream.close();
    }
}
