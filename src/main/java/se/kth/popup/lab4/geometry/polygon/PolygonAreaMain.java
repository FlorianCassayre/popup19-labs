package se.kth.popup.lab4.geometry.polygon;

import se.kth.popup.lab4.geometry.Vector;

import java.util.Locale;
import java.util.Scanner;

public class PolygonAreaMain {
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
            final double algebraic = polygon.getAlgebraicArea();

            System.out.print(algebraic >= 0.0 ? "CCW" : "CW");
            System.out.println(String.format(Locale.US, " %.1f", Math.abs(algebraic)));
        }

        scanner.close();
    }
}
