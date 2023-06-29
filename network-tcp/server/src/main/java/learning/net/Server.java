package learning.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225, 2000);

        Map<String, Greetable> handlers = loadHandlers();

        System.out.println("Server is started");

        while(true) {
            Socket accept = socket.accept();
            new SimpleServer(accept, handlers).start();
        }
    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String, Greetable> result = new HashMap<>();
        try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")){
            Properties prop = new Properties();
            prop.load(is);

            for(Object command : prop.keySet()) {
                String className = prop.getProperty(command.toString());

                Class<Greetable> cl = (Class<Greetable>) Class.forName(className);

                Greetable handler = cl.getConstructor().newInstance();

                result.put(command.toString(), handler);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

class SimpleServer extends Thread {
    private Socket accept;
    private Map<String, Greetable> handlers;

    public SimpleServer(Socket accept, Map<String, Greetable> handlers) {
        this.accept = accept;
        this.handlers = handlers;
    }
    @Override
    public void run() {
        handleRequest();
    }
    private void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

            String request = br.readLine();

            String[] strSplit = request.split("\\s+");
            String command = strSplit[0];
            String userName = strSplit[1];

            System.out.println("Server got string 1: " + command);
            System.out.println("Server got string 2: " + userName);
//            Thread.sleep(2000);


            String response = buildResponce(command, userName);

            bw.write(response);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            accept.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String buildResponce(String command, String userName) {
        Greetable handler = handlers.get(command);
        if(handler != null) {
            return handler.buildResponse(userName);
        }
        return "Hello " + userName;
    }
}
