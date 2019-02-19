/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.minspantree;

import java.util.*;

public final class MinimumSpanningTree {
    private MinimumSpanningTree() {}

    /**
     * Computes the minimum spanning tree of an undirected graph.
     * @param n the number of vertices
     * @param edges the undirected edges
     * @return the corresponding tree and its total weight if the solution exists,
     * otherwise <code>null</code>. Edges are sorted in lexicographical order.
     */
    public static Solution mst(int n, List<Edge> edges) {
        // Prim's algorithm with priority queue

        final List<List<Edge>> adjacency = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
            adjacency.add(new ArrayList<>());

        for(Edge edge : edges) {
            adjacency.get(edge.u).add(edge);
            adjacency.get(edge.v).add(edge);
        }

        final BitSet visited = new BitSet(n);
        final List<Edge> tree = new ArrayList<>(n - 1);

        final PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        final int initial = 0;
        visited.set(initial);
        queue.addAll(adjacency.get(initial));

        int weight = 0;

        while(!queue.isEmpty()) { // While there are still potential vertices to connect
            final Edge e = queue.poll(); // The edge which vertex is the closest to that tree

            final int v; // The vertex outside the tree
            if(!visited.get(e.v))
                v = e.v;
            else if(!visited.get(e.u))
                v = e.u;
            else
                continue; // Would create a cycle

            tree.add(e);
            weight += e.weight;
            visited.set(v);

            queue.addAll(adjacency.get(v)); // Update the queue by adding new vertices that can be connected
        }

        return tree.size() == n - 1 ? new Solution(tree, weight) : null; // A spanning tree is guaranteed to have n-1 edges
    }

    public static final class Solution {
        public final List<Edge> edges;
        public final int weight;

        private Solution(List<Edge> edges, int weight) {
            Collections.sort(edges);
            this.edges = Collections.unmodifiableList(edges);
            this.weight = weight;
        }
    }

    public static final class Edge implements Comparable<Edge> {
        public final int u, v;
        public final int weight;

        public Edge(int u, int v, int weight) {
            this.u = Math.min(u, v);
            this.v = Math.max(u, v);
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge that) {
            if(this.u != that.u)
                return Integer.compare(this.u, that.u);
            else
                return Integer.compare(this.v, that.v);
        }
    }
}
