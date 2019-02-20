/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath.minspantree;

import se.kth.popup.Kattio;

import java.util.ArrayList;
import java.util.List;

public class MinimumSpanningTreeMain {

    public static void run(Kattio kattio) {

        while(true) {
            final int n = kattio.getInt(), m = kattio.getInt();

            if(n == 0 && m == 0)
                break;

            final List<Edge> edges = new ArrayList<>(m);
            for(int i = 0; i < m; i++) {
                final int u = kattio.getInt(), v = kattio.getInt(), w = kattio.getInt();

                final Edge edge = new Edge(u, v, w);

                edges.add(edge);
            }

            final Solution solution = MinimumSpanningTree.mst(n, edges);

            if(solution != null) {
                kattio.println(solution.weight);
                for(Edge edge : solution.edges)
                    kattio.println(edge.u + " " + edge.v);
            } else {
                kattio.println("Impossible");
            }

        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
