package com.netty.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class oldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(19999);
        serverSocket.bind(inetSocketAddress);
        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream  = (DataInputStream) socket.getInputStream();
            byte [] buffer = new byte[4096];
            int readCount = 0;
            try{
                while (readCount != -1){
                    readCount = dataInputStream.read(buffer,0,buffer.length);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
