package se.kth.popup.equationsolver;

import se.kth.popup.Kattio;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EquationSolverMain {

    public static void run(Kattio kattio) {

        int n;
        while((n = kattio.getInt()) != 0) {

            final double[][] a = new double[n][n];

            for(int j = 0; j < n; j++) {
                for(int i = 0; i < n; i++) {
                    a[i][j] = kattio.getDouble();
                }
            }

            final double[] b = new double[n];

            for(int i = 0; i < n; i++) {
                b[i] = kattio.getDouble();
            }

            EquationSolver.EquationSolution solution = EquationSolver.solve(a, b);

            if(solution.isConsistent()) {
                if(solution.isUnique()) {
                    kattio.println(Arrays.stream(solution.getSolution()).map(String::valueOf).collect(Collectors.joining(" ")));
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
