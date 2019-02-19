package se.kth.popup.lab2.shortestpath;

import java.util.List;

public final class FloydWarshall {
    private FloydWarshall() {}

    public static int[][] allPairsShortestPaths(int n, List<Edge> edges) {
        final int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = i == j ? 0 : Integer.MAX_VALUE;
        for(Edge edge : edges)
            matrix[edge.u][edge.v] = (edge.u == edge.v && edge.weight > 0) ? 0 : edge.weight;

        fill(n, matrix);

        final int[][] copy = new int[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                copy[i][j] = matrix[i][j];

        fill(n, matrix);

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(matrix[i][j] < copy[i][j])
                    matrix[i][j] = Integer.MIN_VALUE;

        return matrix;
    }

    private static void fill(int n, int[][] matrix) {
        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++) {
                    final int a = matrix[i][k], b = matrix[k][j],
                            sum = a < Integer.MAX_VALUE && b < Integer.MAX_VALUE ? a + b : Integer.MAX_VALUE;
                    matrix[i][j] = Math.min(matrix[i][j], sum);
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
