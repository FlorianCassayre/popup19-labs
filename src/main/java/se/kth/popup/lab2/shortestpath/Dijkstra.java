/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath;

import java.util.*;

public final class Dijkstra {
    private Dijkstra() {}

    /**
     * Finds the shortest paths from a single source using Dijkstra.
     * @param n the number of vertices
     * @param edges the weighted directed edges
     * @param s the source vertex
     * @return the shortest distances to each target if it exists,
     * or <code>Integer.MAX_VALUE</code> otherwise; as well as the
     * parent vertex or <code>-1</code> if it is the root of the tree
     * or if there exist no shortest path to that vertex.
     */
    public static Solution shortestPath(int n, List<Edge> edges, int s) {
        // (similar to MST, Dijkstra with a priority queue)

        final List<List<Edge>> adjacency = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
            adjacency.add(new ArrayList<>());

        for(Edge edge : edges)
            adjacency.get(edge.u).add(edge);

        final int[] distances = new int[n], parents = new int[n];
        for(int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        final PriorityQueue<Vertex> queue = new PriorityQueue<>();

        queue.add(new Vertex(s, -1, 0));

        while(!queue.isEmpty()) {
            final Vertex vertex = queue.poll();

            if(distances[vertex.v] == Integer.MAX_VALUE && vertex.distance < distances[vertex.v]) {
                distances[vertex.v] = vertex.distance;
                parents[vertex.v] = vertex.parent;

                for(Edge edge : adjacency.get(vertex.v))
                    queue.add(new Vertex(edge.v, edge.u, vertex.distance + edge.weight));
            }
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

    private static final class Vertex implements Comparable<Vertex> {
        private final int v, parent;
        private final int distance;

        private Vertex(int v, int parent, int distance) {
            this.v = v;
            this.parent = parent;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex that) {
            return Integer.compare(this.distance, that.distance);
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
