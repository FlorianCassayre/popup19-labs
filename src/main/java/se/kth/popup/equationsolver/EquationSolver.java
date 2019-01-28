/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.equationsolver;

import java.util.Arrays;

public final class EquationSolver {
    private EquationSolver() {}

    /**
     * Solves a linear equation system of the form Ax = b.
     * @param n the size of the matrix
     * @param a a <code>n * (n + 1)</code> square matrix
     * @return a solution object
     */
    public static EquationSolution solve(int n, double[][] a) {
        if(a.length != n)
            throw new IllegalArgumentException();
        for(int i = 0; i < n; i++)
            if(a[i].length != n + 1)
                throw new IllegalArgumentException();

        int r = -1;
        for(int j = 0; j < n; j++) {
            int k = j;
            for(int i = j + 1; i < n; i++)
                if(Math.abs(a[i][j]) > Math.abs(a[k][j]))
                    k = i;

            if(nonZero(a[k][j])) {
                r++;
                final double val = a[k][j];
                for(int i = 0; i < n + 1; i++) // Divide row
                    a[k][i] /= val;
                for(int i = 0; i < n + 1; i++) { // Swap rows
                    double temp = a[k][i];
                    a[k][i] = a[r][i];
                    a[r][i] = temp;
                }
                for(int i = 0; i < n; i++) { // Reduce upper rows
                    final double v = a[i][j];
                    if(i != r)
                        for(int l = 0; l < n + 1; l++)
                            a[i][l] -= a[r][l] * v;
                }
            }
        }

        return new EquationSolution(n, r + 1, a);
    }

    private static boolean nonZero(double x) {
        return Math.abs(x) > 1e-60;
    }

    public static final class EquationSolution {
        private final double[] solution;
        private final boolean consistent;

        public EquationSolution(int n, int rank, double[][] matrix) {
            if(nonZero(matrix[n - 1][n - 1])) { // Solution exists and is unique
                solution = new double[n];
                for(int i = 0; i < n; i++) {
                    solution[i] = matrix[i][n];
                }
                consistent = true;
            }
            else { // Solution might exist, but if so it is not unique
                solution = null;
                boolean inconsistent = false;
                for(int i = rank; i < n && !inconsistent; i++)
                    for(int j = 0; j < n + 1 && !inconsistent; j++)
                        if(nonZero(matrix[i][j]))
                            inconsistent = true;
                consistent = !inconsistent;
            }

        }

        public double[] getSolution() {
            if(!consistent || solution == null)
                throw new IllegalStateException("Solution is not properly defined");
            return solution;
        }

        public boolean isConsistent() {
            return consistent;
        }

        public boolean isUnique() {
            return solution != null;
        }

        @Override
        public String toString() {
            if(isConsistent())
                if(isUnique())
                    return Arrays.toString(solution);
                else
                    return "multiple";
            else
                return "inconsistent";
        }
    }
}
