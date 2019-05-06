package com.netty.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("text/NioTest9.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,6);
        mappedByteBuffer.put(0,(byte) 'Z');
        mappedByteBuffer.put(4,(byte) '!');

        channel.close();
        randomAccessFile.close();
    }
}
