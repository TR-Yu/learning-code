package com.yu.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用socket编程通信模拟了 HTTP Server的处理过程
 *
 * @author YU
 * @date 2022-05-23 9:17
 */
public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server is running...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("connected from " + socket.getRemoteSocketAddress());
            Handler handler = new Handler(socket);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }
}
