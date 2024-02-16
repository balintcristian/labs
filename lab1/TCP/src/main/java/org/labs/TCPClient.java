package org.labs;

import java.io.*;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        if (args.length ==0) {
            System.out.println("Usage: java TCPClient <server_address> <port>");
            return;
        }
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader keyboardReader = null;
        // Connect to the server...
        try{
            socket = new Socket(args[0],Integer.parseInt(args[1]));
            // Obtain the streams...
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(IOException e)
        {
            System.out.println("Problems initialising client: "+e);
            System.exit(1);
        }
        try {
            // Start the listening thread...
            TCPEchoReader reader = new TCPEchoReader(dataInputStream);
            reader.setDaemon(true);
            reader.start();
            String input;
            while(true)
            {
                // read data in from the keyboard
                input = keyboardReader.readLine();
                // send data to server
                dataOutputStream.writeUTF(input);
            }
        }
        catch(IOException e) { }
        try {
            socket.close();
        }
        catch(IOException e) { }
    }
}