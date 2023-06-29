package learning.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 5; i++) {
            SimpleClass sc = new SimpleClass(i);
            sc.start();
        }
    }

}

class SimpleClass extends Thread {
    public static final String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING"
    };
    private int cmdNumber;

    public SimpleClass (int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }
    @Override
    public void run() {
        try {
//            System.out.println("Started : " + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " " + "Alex";

            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got answer from server : " + answer);

            br.close();
            bw.close();

            socket.close();
//            System.out.println("Finished : " + LocalDateTime.now());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
