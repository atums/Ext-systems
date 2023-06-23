package com.apys.learning.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225, 2000);

        System.out.println("Server is started");

        while(true) {
            Socket accept = socket.accept();
            new SimpleServer(accept).start();
        }
    }
}

class SimpleServer extends Thread {
    private Socket accept;

    public SimpleServer(Socket accept) {
        this.accept = accept;
    }
    @Override
    public void run() {
        handleRequest();
    }
    private void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

            StringBuilder sb = new StringBuilder("Hello, ");
            String str = br.readLine();
            System.out.println("Server got string : " + str);
            Thread.sleep(2000);
            sb.append(str);

            bw.write(sb.toString());
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            accept.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
