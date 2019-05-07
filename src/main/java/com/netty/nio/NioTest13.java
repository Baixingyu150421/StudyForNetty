package com.netty.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {
    public static void main(String[] args) throws IOException {
        String inputFile = "text/NioTest13.txt";
        String outputFile = "text/NioTest13(copy).txt";
        RandomAccessFile randomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outRandomFile = new RandomAccessFile(outputFile,"rw");
        FileChannel channel = randomAccessFile.getChannel();
        FileChannel outRandomFileChannel = outRandomFile.getChannel();
        long length = new File(inputFile).length();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY,0,length);
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();
        CharBuffer decode = charsetDecoder.decode(mappedByteBuffer);
        ByteBuffer byteBuffer = charsetEncoder.encode(decode);
        int write = outRandomFileChannel.write(byteBuffer);
        System.out.println("写入：" + write + "字节");
    }
}
