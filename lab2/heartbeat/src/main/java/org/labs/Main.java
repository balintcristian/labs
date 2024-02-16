package org.labs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Main {
    public static int port;
    public static String remoteIp;
    public static int remotePort;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Heartbeat <?local_server_port>  <*remote_address> <*remote_port>");
            return;
        }
        port = Integer.parseInt(args[0]);
        // ip-ul si portul pe care ruleaza un alt peer
        remoteIp = args[1];
        remotePort = Integer.parseInt(args[2]);
        //pornim "serverul local" al acestui peer
        new Server().start();
        boolean peerDown = false;
        int beatsMissed = 0; // pulsuri ratate
        do {
            try {
                Thread.sleep(5000); // se trimite heartbeat din 5 in 5 secunde
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String response = sendMessage(remoteIp, remotePort, "heartbeat");
            if (response.equals("viu")){
                beatsMissed = 0; //se reseteaza la fiecare puls captat
            }
            else {
                beatsMissed++;
                // un peer este considerat deconectat daca nu raspunde la 5 verificari ale pulsului consecutive
                if (beatsMissed == 5){
                    peerDown = true;
                    System.out.println("Niciun \"puls\" detectat");
                }
            }
        } while (!peerDown);
    }
    public static String sendMessage(String remIP, int remPort, String Message) {
        Socket socketClient;
        BufferedReader input;
        PrintWriter output;
        try {
            // socket cu peer-ul invecinat
            socketClient = new Socket(remIP, remPort);
            //reader si writer pentru manipularea fluxului de date de intrare sau iesire prin socket
            input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            output = new PrintWriter(socketClient.getOutputStream(), true);

            output.println(Message);
            String raspuns = input.readLine();

            System.out.println("Raspuns primit:");
            System.out.println(raspuns);
            return raspuns;
        } catch (IOException e) {
            System.out.println("eroare detectarea puls la: " + remIP + ":" + remPort);
            return "";
        }
    }
}