package se.kth.popup.lab3.recurrence;

import se.kth.popup.lab3.arithmetic.ModularArithmetic;

import java.util.Arrays;

public final class LinearRecurrence {
    private LinearRecurrence() {}

    public static long solve(long[] coefficients, long[] initial, long t, long m) {
        final ModularArithmetic group = new ModularArithmetic(m);

        final int n = initial.length;

        long[][] p = new long[n + 1][n + 1];
        for(int i = 0; i < n - 1; i++)
            p[i][i + 1] = 1;
        for(int i = 0; i < n; i++)
            p[n - 1][i] = coefficients[i + 1];
        p[n - 1][n] = coefficients[0];
        p[n][n] = 1;

        long[][] x = new long[n + 1][n + 1];
        for(int i = 0; i < n + 1; i++)
            x[i][i] = 1;

        while(t > 0) {
            if(t % 2 == 1) {
                x = multiply(x, p, group);
            }
            p = multiply(p, p, group);
            t >>= 1;
        }

        final long[][] homogenous = new long[n + 1][1];
        for(int i = 0; i < n; i++)
            homogenous[i][0] = initial[i];
        homogenous[n][0] = 1;

        final long[][] result = multiply(x, homogenous, group);

        return result[0][0]; // FIXME
    }

    private static long[][] multiply(long[][] a, long[][] b, ModularArithmetic group) {
        final long[][] result = new long[a.length][b[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b[0].length; j++) {
                long acc = 0;
                for(int k = 0; k < a[0].length; k++)
                    acc = group.add(acc, group.multiply(a[i][k], b[k][j]));
                result[i][j] = acc;
            }
        }
        return result;
    }
}
