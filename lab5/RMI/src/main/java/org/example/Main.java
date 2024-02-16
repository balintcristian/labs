package org.example;

import java.rmi.Naming;

public class Main {
    public static void main(String[] args) {
        try {

            Square square = (Square) Naming.lookup("rmi://localhost/SquareCalculator");
            Rectangle rectangle = (Rectangle) Naming.lookup("rmi://localhost/RectangleCalculator");

            double[] squareValues = {3,3,3,3};
            double[] rectValues = {3,6,3,6};

            double perimPatrat = square.calculatePerimeter(squareValues);
            double ariePatrat = square.calculateSurfaceArea(squareValues);
            double perimDreptunghi = rectangle.calculatePerimeter(rectValues);
            double arieDreptunghi = rectangle.calculateSurfaceArea(rectValues);
            System.out.print("Laturile patratului: \n");
            for (int i = 0; i < squareValues.length; i++) {
                System.out.print(i+" : "+squareValues[i] + "\n");
            }
            System.out.print("\nPerimetrul patratului = " + perimPatrat + " Aria patratului: "+ ariePatrat+"\n\n");

            System.out.print("Laturile dreptunghiului: \n");
            for (int i = 0; i < rectValues.length; i++) {
                System.out.print(i+" : "+rectValues[i] + "\n");
            }
            System.out.print("\nPerimetrul dreptunghiului = " + perimDreptunghi + " Aria dreptunghiului: "+ arieDreptunghi );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}