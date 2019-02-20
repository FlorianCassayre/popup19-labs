package se.kth.popup.lab2.shortestpath.floydwarshall;

import se.kth.popup.Kattio;

import java.util.ArrayList;
import java.util.List;
import se.kth.popup.lab2.Edge;

public class AllPairsMain {
    public static void run(Kattio kattio) {

        while(true) {
            final int n = kattio.getInt(), m = kattio.getInt(), q = kattio.getInt();

            if(n == 0 && m == 0 && q == 0)
                break;

            final List<Edge> edges = new ArrayList<>(m);
            for(int i = 0; i < m; i++) {
                final int u = kattio.getInt(), v = kattio.getInt(), weight = kattio.getInt();
                edges.add(new Edge(u, v, weight));
            }

            final int[][] matrix = FloydWarshall.allPairsShortestPaths(n, edges);

            for(int i = 0; i < q; i++) {
                final int u = kattio.getInt(), v = kattio.getInt();
                final int result = matrix[u][v];
                if(result == Integer.MAX_VALUE)
                    kattio.println("Impossible");
                else if(result == Integer.MIN_VALUE)
                    kattio.println("-Infinity");
                else
                    kattio.println(result);
            }

            kattio.println();
        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
