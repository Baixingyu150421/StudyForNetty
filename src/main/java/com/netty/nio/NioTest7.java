package com.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer 与 可读写的buffer 具体的实现可以是 HeapByteBuffer 也可以是DirectByteBuffer
 * 只能从普通转化成只读
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        for(int i = 0 ; i < byteBuffer.capacity() ; i++){
            byteBuffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
    }
}
