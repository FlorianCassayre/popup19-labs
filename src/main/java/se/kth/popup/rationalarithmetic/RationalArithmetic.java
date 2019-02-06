/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.rationalarithmetic;

import se.kth.popup.Kattio;

import java.util.function.BinaryOperator;

public class RationalArithmetic {

    public static void run(Kattio kattio) {
        final int n = kattio.getInt();

        for(int i = 0; i < n; i++) {
            final int na = kattio.getInt(), da = kattio.getInt();
            final String operator = kattio.getWord();
            final int nb = kattio.getInt(), db = kattio.getInt();

            final BigRational ra = BigRational.valueOf(na, da), rb = BigRational.valueOf(nb, db);

            final BigRational result = parseOperator(operator).apply(ra, rb);

            kattio.println(result);
        }

        kattio.close();
    }

    private static BinaryOperator<BigRational> parseOperator(String operator) {
        switch(operator) {
            case "+":
                return BigRational::add;
            case "-":
                return BigRational::subtract;
            case "*":
                return BigRational::multiply;
            case "/":
                return BigRational::divide;
            default:
                throw new IllegalArgumentException(operator);
        }
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
