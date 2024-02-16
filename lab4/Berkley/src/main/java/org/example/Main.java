package org.example;

import java.util.ArrayList;
import java.util.List;

class BerkeleyServer {
    private List<Integer> clocks;
    private int avgTime;

    public BerkeleyServer(List<Integer> clocks) {
        this.clocks = clocks;
        this.avgTime=systemAvgTime();
    }
    public int systemAvgTime() {
        // practic algoritmul se baseaza pe gasirea unui timp mediu care defapt este o discrepanta medie intre ceasuri
        // care ulterior o folosim pentru a calibra ceasurile clientilor (eu asta am inteles)
        int sum = 0;
        for (int clock : clocks) {
            sum += clock;
        }
        return sum / clocks.size();
    }
    public int getSystemAvg(){
        return this.avgTime;
    };

    private void updateSystemAvgTime(){
        this.avgTime=systemAvgTime();
    }
}

class BerkeleyClient {
    //un client are o variabila pentru ceas(am pus int precum unor secunde),
    // constructor,getter si setter pentru acelasi ceas
    private int clock;

    public BerkeleyClient(int clock) {
        this.clock = clock;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}

public class Main {
    private static BerkeleyServer getBerkeleyServer() {
        List<Integer> clocks = new ArrayList<>();
        // reprezinta o lista intiala de valori are ceasurilor pentru fiecare membru al sistemului distibuit
        clocks.add(2); //setam niste valori initiale ale "ceasurilor" din sistem
        clocks.add(1);//1
        clocks.add(13);//2 comentarea acestor doua ceasuri ar duce la practic un server normal cu un
        // singur station in locul mai multor peers cu ceasuri indivduale respectiv un timp mediu dat de o singura statie
        return new BerkeleyServer(clocks);
    }
    public static void main(String[] args) {
        BerkeleyServer server = getBerkeleyServer();
        System.out.println("System average time: "+server.getSystemAvg());
        List<BerkeleyClient> clients = new ArrayList<>();
        clients.add(new BerkeleyClient(2));
        clients.add(new BerkeleyClient(3));
        clients.add(new BerkeleyClient(1));
        // Add as many clients as needed
        for (BerkeleyClient client : clients) {
            System.out.println("Client clock before synchronization " + client.getClock());
        }
        // Asumam ca clientii vor sa-si sincronizeze ceasurile fata de sistem
        int avgClock = server.getSystemAvg();
        // actualizam ceasurile in functie de diferentele fata de acel timp mediu
        // o consecinta este ca fiecare ceas ajunge defapt la acel average time sau clock de pe server/sistem
        for (BerkeleyClient client : clients) {
            int diff = avgClock - client.getClock();
            client.setClock(client.getClock() + diff);
            System.out.println("Client clock synchronized to: " + client.getClock());
        }
    }


}