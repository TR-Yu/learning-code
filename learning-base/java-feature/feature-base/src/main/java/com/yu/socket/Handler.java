package com.yu.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 类似 servelet 处理类 ：任务：
 *
 * @author YU
 * @date 2022-05-23 9:24
 */
public class Handler implements Runnable{
    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("start");
        try {
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            handle(inputStream, outputStream);
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                System.out.println(e.getCause().getMessage());
            }
            System.out.println("client discounnected");
        }
    }

    /**
     * 具体处理Http请求内容函数
     *
     * @author YU
     * @param inputStream  {@link InputStream}
     * @param outputStream  {@link OutputStream}
     */
    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        System.out.println("Process new http request...");
        // 读取器和写器
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

        boolean requestOK = false;
        // 按照http的请求报文格式依次读取
        // 请求行 （request line : 请求方法 URL 1.1\n\r ; 请求方法：GET, POST, HEAD, PUT, DELETE, OPTIONS, TRACE, CONNECT
        // 请求头部： (header) 头部字段名:值\n\r
        //
        String first = reader.readLine();
        System.out.println("firest: " + first);
        if (first.startsWith("GET / HTTP/1.")){
            requestOK = true;
        }
        for (;;){
            String header = reader.readLine();
            if (header.isEmpty()) {
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOK ? "Response OK" : "Response Error");
        if (!requestOK){
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            String data = "<html><body><h1>Hello, world!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            // 空行标识Header和Body的分隔
            writer.write("\r\n");
            writer.write(data);
            writer.flush();
        }
    }
}
