package se.kth.popup.lab1.equationsolver;

import org.junit.jupiter.api.Test;
import se.kth.popup.KattisTester;

import java.util.Arrays;

public class EquationSolverTest extends KattisTester {

    EquationSolverTest() {
        super("equationsolver", EquationSolverMain::run);
    }

    @Test
    void test1() {
        final double[][] matrix = new double[][] { {0.0, 0.01} };
        System.out.println(EquationSolver.solve(1, matrix));
    } // 2/7, 6/7, 3/7

    @Test
    void test() {
        final double[][] matrix = new double[][] { {2, -1, 3, 1}, {1, 3, -2, 2}, {3, 0, 5, 3} };
        System.out.println(Arrays.toString(EquationSolver.solve(3, matrix).getSolution()));
    } // 2/7, 6/7, 3/7
}
