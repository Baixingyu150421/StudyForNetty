package com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * DirectByteBuffer
 */
public class NioTest8 {
    public static void main(String[] args) throws  Exception{
        FileInputStream inputStream = new FileInputStream("text/NioTest8.txt");
        FileOutputStream outputStream = new FileOutputStream("text/NioTest8(copy).txt");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        while (true){
            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            System.out.println("可读字节数：" + read);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
    }
}
