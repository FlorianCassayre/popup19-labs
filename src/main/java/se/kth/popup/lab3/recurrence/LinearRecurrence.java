/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.recurrence;

import se.kth.popup.lab3.arithmetic.ModularArithmetic;

public final class LinearRecurrence {
    private LinearRecurrence() {}

    public static long solve(long[] coefficients, long[] initial, long t, long m) {
        final ModularArithmetic group = new ModularArithmetic(m);

        final int n = initial.length;

        long[][] p = new long[n + 1][n + 1];
        for(int i = 0; i < n - 1; i++)
            p[i][i + 1] = 1;
        for(int i = 0; i < n; i++)
            p[n - 1][i] = group.normalize(coefficients[n - i]);
        p[n - 1][n] = group.normalize(coefficients[0]);
        p[n][n] = 1;

        long[][] x = new long[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++)
            x[i][i] = 1;

        while(t > 0) {
            if(t % 2 == 1) {
                x = multiply(x, p, group);
                t--;
            } else {
                p = multiply(p, p, group);
                t >>= 1;
            }
        }

        final long[][] homogenous = new long[n + 1][1];
        for(int i = 0; i < n; i++)
            homogenous[i][0] = group.normalize(initial[i]);
        homogenous[n][0] = 1;

        final long[][] result = multiply(x, homogenous, group);

        return result[0][0];
    }

    private static long[][] multiply(long[][] a, long[][] b, ModularArithmetic group) {
        final long[][] result = new long[a.length][b[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b[0].length; j++) {
                long acc = 0;
                for(int k = 0; k < a[0].length; k++)
                    acc = group.normalize(acc + a[i][k] * b[k][j]);
                result[i][j] = acc;
            }
        }
        return result;
    }
}
