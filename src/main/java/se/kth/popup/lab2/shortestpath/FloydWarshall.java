/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.shortestpath;

import java.util.List;
import static java.lang.Integer.*;

public final class FloydWarshall {
    private FloydWarshall() {}

    /**
     * Computes the shortest distances for all pairs of vertices.
     * Negative weights are allowed and negative cycles will be detected.
     * Produces the same result as {@link BellmanFord} but slightly more efficiently for all pairs.
     * @param n the number of vertices
     * @param edges the edges
     * @return a solution containing the costs and the paths;
     * a distance of <code>Integer.MAX_VALUE</code> indicates an impossible path
     * while <code>Integer.MIN_VALUE</code> means that the cost of the path can be
     * made arbitrarily low.
     */
    public static int[][] allPairsShortestPaths(int n, List<Edge> edges) {
        final int[][] matrix = new int[n][n]; // The adjacency/result matrix
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = i == j ? 0 : MAX_VALUE;
        for(Edge edge : edges)
            if(edge.u != edge.v || edge.weight < 0) // Self loops must be 0 or negative
                matrix[edge.u][edge.v] = Math.min(edge.weight, matrix[edge.u][edge.v]);

        // Applying the Floyd-Warshall recurrence
        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(matrix[i][k] < MAX_VALUE && matrix[k][j] < MAX_VALUE)
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);

        // Find all the negative cycles
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 0; k < n; k++) {
                    if(matrix[i][j] == MAX_VALUE)
                        break;
                    if(matrix[i][k] < MAX_VALUE && matrix[k][j] < MAX_VALUE && matrix[k][k] < 0)
                        matrix[i][j] = MIN_VALUE;
                }

        return matrix;
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
