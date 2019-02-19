/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath;

import se.kth.popup.Kattio;

import java.util.ArrayList;
import java.util.List;

public class NonNegativeWeightsMain {
    public static void run(Kattio kattio) {
        while(true) {
            final int n = kattio.getInt(), m = kattio.getInt(), q = kattio.getInt(), s = kattio.getInt();

            if(n == 0 && m == 0 && q == 0 && s == 0)
                break;

            final List<Dijkstra.Edge> edges = new ArrayList<>(m);
            for(int i = 0; i < m; i++) {
                final int u = kattio.getInt(), v = kattio.getInt(), w = kattio.getInt();
                edges.add(new Dijkstra.Edge(u, v, w));
            }

            final Dijkstra.Solution solution = Dijkstra.shortestPath(n, edges, s);

            for(int i = 0; i < q; i++) {
                final int to = kattio.getInt();

                final int distance = solution.distances[to];
                if(distance == Integer.MAX_VALUE) {
                    kattio.println("Impossible");
                } else {
                    kattio.println(distance);
                }
            }

            kattio.println();
        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
