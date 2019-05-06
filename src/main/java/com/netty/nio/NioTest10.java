package com.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("text/NioTest9.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        FileLock lock = channel.lock(2,5,true);
        System.out.println("valid:" + lock.isValid());
        System.out.println("shared：" + lock.isShared());
        channel.close();
        randomAccessFile.close();
    }
}
