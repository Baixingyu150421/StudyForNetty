package com.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用Nio实现文件复制
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("text/NioTest4.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("text/NioTest4(output).txt");
        FileChannel ischannel = fileInputStream.getChannel();
        FileChannel oschannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while(true){
            // 注意 循环一次写完不调用clear position为49 ，会持续写入output文件中
            byteBuffer.clear();
            int read = ischannel.read(byteBuffer);
            System.out.println("字节数：" + read);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            oschannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
