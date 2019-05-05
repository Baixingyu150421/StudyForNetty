package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufTest2 {
    public static void main(String[] args) {
        //编码
        ByteBuf byteBuf = Unpooled.copiedBuffer("宇I am yushen", Charset.forName("utf-8"));
        //是true
        if(byteBuf.hasArray()){
            //可以拿到字节数组
            byte[] array = byteBuf.array();
            //解码
            System.out.println(new String(array,Charset.forName("utf-8")));
            //类型：UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 11, cap: 33)
            // src.remaining() * encoder.maxBytesPerChar()  capacity的计算方式  utf-8的中文字符三个bit位存储
            System.out.println(byteBuf);

            System.out.println("第一个字节在数组中的偏移量："+byteBuf.arrayOffset()+"读取指针的索引位置：" + byteBuf.readerIndex()
                    + "写指针的索引位置："+byteBuf.writerIndex() + "byteBuf的容量："+ byteBuf.capacity());
            System.out.println("可读的长度："+byteBuf.readableBytes());
            System.out.println("可写的长度："+byteBuf.writableBytes());

//            for (int i = 0 ; i < byteBuf.readableBytes() ; i++){
//                //没解码
//                System.out.print((char)byteBuf.getByte(i));
//            }
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));


        }

    }
}
