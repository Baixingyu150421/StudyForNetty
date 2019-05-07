package com.netty.nio;

import java.nio.ByteBuffer;

/**
 * 类型化的get/put
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.putInt(5);
        byteBuffer.putDouble(14.556);
        byteBuffer.putLong(454535L);
        byteBuffer.putChar('a');
        byteBuffer.putShort((short) 3);
        //如何写入就如何读取
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
