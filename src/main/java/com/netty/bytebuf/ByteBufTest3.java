package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class ByteBufTest3 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        ByteBuf byteBuf1 = Unpooled.directBuffer(10);
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        compositeByteBuf.addComponents(byteBuf,byteBuf1);
        compositeByteBuf.removeComponent(0);
//        Iterator<ByteBuf> iterable = compositeByteBuf.iterator();
//        while (iterable.hasNext()){
//            System.out.println(iterable.next());
//        }
        //函数式编程
        compositeByteBuf.forEach(System.out::println);
    }
}
