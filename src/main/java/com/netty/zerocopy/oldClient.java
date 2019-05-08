package com.netty.zerocopy;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class oldClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",19999);
        socket.connect(inetSocketAddress);
        String filename = "xxxxx";
        FileInputStream fileInputStream = new FileInputStream(filename);
        DataOutputStream dataOutputStream = (DataOutputStream) socket.getOutputStream();
        long total = 0;
        int readByte = 0;
        byte [] buffer = new byte[4096];
        long currentTimeMillis = System.currentTimeMillis();
        while ((readByte = fileInputStream.read(buffer) )!= -1){
            dataOutputStream.write(buffer,0,buffer.length);
            total += readByte;
        }
        System.out.println("传输字节：" + total + "耗时：" + (System.currentTimeMillis() - currentTimeMillis ));
        dataOutputStream.close();
        fileInputStream.close();
    }
}
