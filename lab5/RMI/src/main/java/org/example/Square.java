package org.example;
import java.rmi.*;

public interface Square extends Remote {
    double calculatePerimeter(double[] a) throws RemoteException;
    double calculateSurfaceArea(double[] a) throws RemoteException;
}

