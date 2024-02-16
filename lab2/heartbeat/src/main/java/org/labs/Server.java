package org.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    @Override
    public void run(){
        BufferedReader input;
        PrintWriter output;
        try {
            ServerSocket serverSocket = new ServerSocket(Main.port);

            while (!Thread.interrupted()){
                Socket socketClient = serverSocket.accept();

                input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                output = new PrintWriter(socketClient.getOutputStream(), true);

                String mesajPrimit = input.readLine();

                String[] split = mesajPrimit.split(" ");

                if (split[0].equals("heartbeat")){
                    output.println("viu");
                }
                input.close();
                output.close();
                socketClient.close();
            }

        } catch (IOException e) {
            System.out.println("server thread error");
            throw new RuntimeException(e);
        }
    }
}
