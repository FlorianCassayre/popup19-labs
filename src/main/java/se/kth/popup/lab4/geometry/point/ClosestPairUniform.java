package se.kth.popup.lab4.geometry.point;

import se.kth.popup.Kattio;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class ClosestPairUniform {
    public static void main(String[] args) {
        final Kattio kattio = new Kattio();

        while(true) {
            final int n = kattio.getInt();

            if(n == 0)
                break;

            final double b = 100000;
            final QuadTree tree = new QuadTree(-b, b, -b, b, 8);

            //long t0 = System.currentTimeMillis();
            //Random random = new Random();
            final QuadTree.Point[] points = new QuadTree.Point[n];
            for(int i = 0; i < n; i++) {
                final QuadTree.Point point = new QuadTree.Point(kattio.getDouble(), kattio.getDouble());
                //final QuadTree.Point point = new QuadTree.Point(random.nextDouble() * 2 * b - b, random.nextDouble() * 2 * b - b);
                points[i] = point;
                tree.insert(point);
            }

            QuadTree.Point u = null, v = null;
            double distance = Double.POSITIVE_INFINITY;
            for(QuadTree.Point point : points) {
                final QuadTree.Point test = tree.closest(point);
                if(u == null) {
                    u = point;
                    v = test;
                } else {
                    final double d = point.distanceSq(test);
                    if(d < distance) {
                        u = point;
                        v = test;
                        distance = d;
                    }
                }
            }

            System.out.println(u.x + " " + u.y + " " + v.x + " " + v.y);
            //long t1 = System.currentTimeMillis();
            //System.out.println(t1 - t0);
        }

        kattio.close();
    }
}
