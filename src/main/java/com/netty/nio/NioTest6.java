package com.netty.nio;

import java.nio.ByteBuffer;

/**
 * 分片buffer slicebuffer与原buffer共享同一数组
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for(int i = 0 ; i < byteBuffer.capacity() ; i++){
            byteBuffer.put((byte)i);
        }

        byteBuffer.position(3);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer = byteBuffer.slice();

        for(int i = 0 ; i< sliceBuffer.capacity() ;i++){
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i,b);
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        for(int i = 0 ; i < byteBuffer.capacity() ; i++){
            System.out.println(byteBuffer.get(i));
        }

    }
}
