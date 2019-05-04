package se.kth.popup.lab4.geometry.point;

import se.kth.popup.lab4.geometry.Vector;

import java.util.Scanner;

public class MaximumCollinearPointsMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while(true) {
            final int n = scanner.nextInt();

            if(n == 0)
                break;

            final Vector[] points = new Vector[n];
            for(int i = 0; i < n; i++) {
                points[i] = new Vector(scanner.nextInt(), scanner.nextInt());
            }

            System.out.println(MaximumCollinearPoints.find(points));
        }

        scanner.close();
    }
}
