/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.eulerianpath;

import java.util.*;

public final class EulerianPath {
    private EulerianPath() {}

    /**
     * Computes a Eulerian path of a graph if it exists.
     * @param n the number of vertices
     * @param edges the edges of this graph
     * @return the order in which the vertices can be visited, or <code>null</code>
     */
    public static List<Integer> findPath(int n, List<Edge> edges) {
        if(edges.size() == 0)
            return Collections.emptyList();

        final int[] degree = new int[n];

        final List<ArrayDeque<Integer>> adjacency = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
            adjacency.add(new ArrayDeque<>());
        for(Edge edge : edges) {
            adjacency.get(edge.u).add(edge.v);
            degree[edge.u]--;
            degree[edge.v]++;
        }

        int pos = -1, neg = -1; // Optional starting and ending vertices (case where the tour does not form a cycle)
        for(int i = 0; i < n; i++) { // Check the degree of each vertex
            final int deg = degree[i];
            if(deg != 0) {
                if(deg == 1 && pos == -1) // Ending vertex
                    pos = i;
                else if(deg == -1 && neg == -1) // Starting vertex
                    neg = i;
                else // Impossible: two vertices of the same non-zero degree
                    return null;
            }
        }

        boolean cyclic = true; // Will the tour form a cycle?
        if(pos != -1 && neg != -1) {
            adjacency.get(pos).add(neg); // Add a temporary fake edge to form a cycle
            cyclic = false;
        } else if(pos != -1 || neg != -1) { // Impossible
            return null;
        }

        final Deque<Integer> exploring = new ArrayDeque<>();
        final List<Integer> history = new ArrayList<>();

        exploring.push(cyclic ? edges.get(0).u : neg); // Base case: start with any vertex that has at least one outward edge

        while(!exploring.isEmpty()) { // While there are still vertices to explore
            while(!adjacency.get(exploring.peek()).isEmpty()) { // While there are still unexplored edges from this vertex
                final int u = exploring.peek();
                final int v = adjacency.get(u).pop(); // Explore this edge and mark the destination to be explored
                exploring.push(v);
            }

            while(!exploring.isEmpty() && adjacency.get(exploring.peek()).isEmpty()) { // Add all the vertices to the path until an unexplored intersection is reached
                history.add(exploring.pop()); // Append this vertex to the path
            }
        }

        final List<Integer> path;

        if(!cyclic) { // Special case
            Collections.reverse(history);
            final List<Integer> old = new ArrayList<>(history);
            path = new ArrayList<>(old.size() - 1);
            int index = -1;
            for(int i = 0; i < old.size(); i++) {
                if(old.get(i) == pos && old.get(i < old.size() - 1 ? i + 1 : 0) == neg) {
                    index = i;
                    break;
                }
            }
            if(index == -1)
                return null;
            for(int i = index + 1; i < old.size() - 1; i++)
                path.add(old.get(i));
            for(int i = 0; i <= index; i++)
                path.add(old.get(i));
        } else {
            path = new ArrayList<>(history);
        }

        return path.size() == edges.size() + 1 ? path : null;
    }

    public static final class Edge {
        public final int u, v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
}
