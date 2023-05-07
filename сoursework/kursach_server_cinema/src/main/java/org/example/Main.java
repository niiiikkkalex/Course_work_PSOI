package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public Main(){

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(!serverSocket.isClosed()) {
                if (br.ready()) {
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        serverSocket.close();
                        break;
                    }
                }
                System.out.println("Server wait connection...");
                Socket client = serverSocket.accept();
                executeIt.execute((Runnable) new RunnableServer(client));
            }

            executeIt.shutdown();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }
}
