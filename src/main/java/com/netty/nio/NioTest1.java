package com.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        //完成底层数组的初始化 把capacity limit position 的值也初始化
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for(int i = 0 ;i < intBuffer.capacity() ; i++){
            int random = new SecureRandom().nextInt(20);
            intBuffer.put(random);
        }
        //读写切换时必须调用
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
