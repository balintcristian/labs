package org.example;

import java.rmi.*;

public interface Rectangle extends Remote {
    double calculatePerimeter(double[] x) throws RemoteException;
    double calculateSurfaceArea(double[] x) throws RemoteException;
}
