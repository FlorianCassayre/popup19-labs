package se.kth.popup.lab4.geometry.polygon;

import se.kth.popup.lab4.geometry.Vector;

import java.util.Scanner;

public class PolygonInsideMain {
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
            final Polygon polygon = new Polygon(points);

            final int m = scanner.nextInt();
            for(int i = 0; i < m; i++) {
                final Vector point = new Vector(scanner.nextInt(), scanner.nextInt());

                final int result = polygon.isInside(point);

                if(result == 0)
                    System.out.println("on");
                else if(result > 0)
                    System.out.println("in");
                else if(result < 0)
                    System.out.println("out");
            }
        }

        scanner.close();
    }
}
