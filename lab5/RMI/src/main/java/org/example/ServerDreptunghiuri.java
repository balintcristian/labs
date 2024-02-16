package org.example;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerDreptunghiuri {
    public static void main(String[] args) {
        try {
            RectangleImp rectlCalc = new RectangleImp();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("RectangleCalculator", rectlCalc);
            System.out.println("Server pentru dreptunghiuri");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}