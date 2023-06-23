package com.apys.learning.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 500; i++) {
            SimpleClass sc = new SimpleClass();
            sc.start();
        }
    }

}

class SimpleClass extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Started : " + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String sb = "Alex";

            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got answer from server : " + answer);

            br.close();
            bw.close();

            socket.close();
            System.out.println("Finished : " + LocalDateTime.now());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
