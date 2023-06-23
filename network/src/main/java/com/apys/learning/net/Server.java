package com.apys.learning.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(25225);

        System.out.println("Server is started");

        while(true) {
            Socket accept = socket.accept();
            handleRequest(accept);
        }
    }

    private static void handleRequest(Socket accept) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

        StringBuilder sb = new StringBuilder("Hello, ");
        String str = br.readLine();
        sb.append(str);

        System.out.println("Server got string : " + str);

        bw.write(sb.toString());
        bw.newLine();
        bw.flush();

        br.close();
        bw.close();

        accept.close();
    }
}
