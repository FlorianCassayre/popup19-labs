/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath;

import java.util.BitSet;
import java.util.List;

public final class BellmanFord {
    private BellmanFord() {}

    /**
     * Computes all the shortest paths from a single source.
     * Negative weights are allowed, and negative cycle will be detected.
     * @param n the number of vertices
     * @param edges the edges
     * @param s the source vertex
     * @return a solution containing the costs and the paths;
     * a distance of <code>Integer.MAX_VALUE</code> indicates an impossible path
     * while <code>Integer.MAX_VALUE</code> means that there is a negatives cycle
     */
    public static Solution shortestPath(int n, List<Edge> edges, int s) {
        final int[] distances = new int[n], parents = new int[n];

        // Initialization
        for(int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        distances[s] = 0; // Source

        // Relaxation
        for(int i = 0; i < 2 * n; i++)
            for(Edge edge : edges)
                if(distances[edge.u] < Integer.MAX_VALUE && distances[edge.u] + edge.weight < distances[edge.v]) {
                    distances[edge.v] = distances[edge.u] + edge.weight;
                    parents[edge.v] = edge.u;
                }

        // Negative cycles
        for(int i = 0; i < n; i++) {
            final BitSet set = new BitSet(n);
            int node = i;
            do {
                if(set.get(node)) {
                    distances[i] = Integer.MIN_VALUE;
                    break;
                } else {
                    set.set(node);
                }
            } while((node = parents[node]) != -1);
        }

        return new Solution(n, distances, parents);
    }

    public static final class Solution {
        public final int n;
        public final int[] distances, parents;

        private Solution(int n, int[] distances, int[] parents) {
            this.n = n;
            this.distances = distances;
            this.parents = parents;
        }
    }

    public static final class Edge {
        public final int u, v;
        public final int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
