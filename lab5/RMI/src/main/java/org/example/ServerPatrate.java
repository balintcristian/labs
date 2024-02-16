package org.example;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerPatrate {
    public static void main(String[] args) {
        try {
            SquareImp squareCalc = new SquareImp();
            LocateRegistry.createRegistry(1098);
            Naming.rebind("SquareCalculator", squareCalc);
            System.out.println("Server pentru patrate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}