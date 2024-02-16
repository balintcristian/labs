package org.labs;
import java.io.IOException;
import java.net.*;

public class UDPClient {
        public static void main(String[] args) {
            final String SERVER_IP = "127.0.0.1";
            final int SERVER_PORT = 3000;

            try (DatagramSocket clientSocket = new DatagramSocket()) {
                InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

                // Message to send to the server
                String message = "Hello from UDP Client";
                byte[] sendData = message.getBytes();

                while(true){
                    // Sending the message to the server
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                    clientSocket.send(sendPacket);
                    System.out.println("Message sent to server: " + message);

                    // Receiving response from the server
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received from server: " + serverResponse);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

