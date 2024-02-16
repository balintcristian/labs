package org.labs;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer {
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            // open a server socket
            serverSocket = new ServerSocket(3000);
            System.out.println("Server created on port " + 3000);
            while (true) {
                System.out.println("Awaiting client connection...");
                // await for a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

                // communicate with the client
                try {
                    dataOutputStream.writeUTF("Welcome to the TCP Echo Server!");
                    String input;
                    while (true) {
                        SimpleDateFormat d=new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat h=new SimpleDateFormat("HH:mm:ss");
                        input = (h.format(new Date())+" "+dataInputStream.readUTF());
                        System.out.println(d.format(new Date())+" "+input);
                        dataOutputStream.writeUTF("Received by server at "+input);
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected from server");
                } finally {
                    // Close the client socket's streams
                    dataInputStream.close();
                    dataOutputStream.close();
                    // Close the client socket
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Problems initializing server: " + e);
            System.exit(1);
        } finally {
            // Close the server socket
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
