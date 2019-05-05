package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 *
 */
public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for(int i = 0 ; i < 10 ; i++){
            //这里面writeByte是相对方法
            byteBuf.writeByte(i);
        }

        for(int i = 0 ; i < byteBuf.capacity() ; i ++){
            //getByte是绝对方法
            // System.out.println(byteBuf.getByte(i));
            //相对方法
            System.out.println(byteBuf.readByte());
        }
    }
}
