/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.equationsolver;

import se.kth.popup.Kattio;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EquationSolverMain {

    public static void run(Kattio kattio) {

        int n;
        while((n = kattio.getInt()) != 0) {

            final double[][] a = new double[n][n + 1];

            for(int j = 0; j < n; j++) {
                for(int i = 0; i < n; i++) {
                    a[j][i] = kattio.getDouble();
                }
            }

            for(int i = 0; i < n; i++) {
                a[i][n] = kattio.getDouble();
            }

            EquationSolver.EquationSolution solution = EquationSolver.solve(n, a);

            if(solution.isConsistent()) {
                if(solution.isUnique()) {
                    kattio.println(Arrays.stream(solution.getSolution()).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
                } else {
                    kattio.println("multiple");
                }
            } else {
                kattio.println("inconsistent");
            }

        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
