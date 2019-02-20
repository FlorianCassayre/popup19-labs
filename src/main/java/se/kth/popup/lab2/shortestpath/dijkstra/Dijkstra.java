/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath.dijkstra;

import java.util.*;
import se.kth.popup.lab2.Edge;

public final class Dijkstra
{
    private Dijkstra() {}

    /**
     * Finds the shortest paths from a single source using Dijkstra.
     * This function supports both regular edges (u, v, w),
     * and time tabled edges (u, v, t0, p, d).
     * @param n the number of vertices
     * @param edges the extended weighted directed edges
     * @param s the source vertex
     * @return the shortest distances to each target if it exists,
     * or <code>Integer.MAX_VALUE</code> otherwise; as well as the
     * parent vertex or <code>-1</code> if it is the root of the tree
     * or if there exist no shortest path to that vertex.
     */
    public static Solution shortestPath(int n, List<Edge> edges, int s)
    {
        // Generalized Dijkstra with a priority queue
        final List<List<Edge>> adjacency = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            adjacency.add(new ArrayList<>());
        }

        for (Edge edge : edges)
        {
            adjacency.get(edge.u).add(edge);
        }

        final int[] times = new int[n], parents = new int[n];
        for(int i = 0; i < n; i++) {
            times[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        final PriorityQueue<Vertex> queue = new PriorityQueue<>();

        queue.add(new Vertex(s, -1, 0));

        while(!queue.isEmpty()) {
            final Vertex vertex = queue.poll();

            if(times[vertex.v] == Integer.MAX_VALUE) {
                times[vertex.v] = vertex.distance;
                parents[vertex.v] = vertex.parent;

                for(Edge edge : adjacency.get(vertex.v)) {
                    final int next = edge.nextAvailable(vertex.distance);
                    if(next < Integer.MAX_VALUE)
                        queue.add(new Vertex(edge.v, edge.u, next + edge.weight));
                }
            }
        }

        return new Solution(n, times, parents);
    }
}
