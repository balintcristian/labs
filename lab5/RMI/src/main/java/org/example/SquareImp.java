package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SquareImp extends UnicastRemoteObject implements Square {
    protected SquareImp() throws RemoteException {
        super();
    }

    @Override
    public double calculatePerimeter(double[] a) throws RemoteException {
        return a[0]*4;
    }
        @Override
        public double calculateSurfaceArea(double[] a) throws RemoteException {
            return a[0]*a[0];
        }
}