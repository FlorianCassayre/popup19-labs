package se.kth.popup.lab2.shortestpath.floydwarshall;

import java.util.List;
import se.kth.popup.lab2.Edge;

public final class FloydWarshall {
    private FloydWarshall() {}

    public static int[][] allPairsShortestPaths(int n, List<Edge> edges) {
        final int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = i == j ? 0 : Integer.MAX_VALUE;
        for(Edge edge : edges)
            matrix[edge.u][edge.v] = (edge.u == edge.v && edge.weight > 0) ? 0 : edge.weight;

        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++) {
                    final int a = matrix[i][k], b = matrix[k][j],
                            sum = a < Integer.MAX_VALUE && b < Integer.MAX_VALUE ? a + b : Integer.MAX_VALUE;
                    matrix[i][j] = Math.min(matrix[i][j], sum);
                }

        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(matrix[k][j] < Integer.MAX_VALUE && matrix[j][j] < 0 && matrix[j][i] < Integer.MAX_VALUE)
                        matrix[k][i] = Integer.MIN_VALUE;

        return matrix;
    }
}
