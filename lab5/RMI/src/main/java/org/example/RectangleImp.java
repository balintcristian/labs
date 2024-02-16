package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RectangleImp extends UnicastRemoteObject implements Rectangle  {
        protected RectangleImp() throws RemoteException {
            super();
        }

        @Override
        public double calculatePerimeter(double[] a) throws RemoteException {
            return (a[0]+a[1])*2;
        }
        @Override
        public double calculateSurfaceArea(double[] a) throws RemoteException {
            return a[0]*a[1];
        }
    }

