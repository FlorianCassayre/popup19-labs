/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.arithmetic;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class ModularArithmeticMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while(true) {
            final String[] line = scanner.nextLine().split(" ");
            final long n = Long.parseLong(line[0]), t = Long.parseLong(line[1]);

            if(n == 0 && t == 0)
                break;

            final ModularArithmetic helper = new ModularArithmetic(n);

            for(int i = 0; i < t; i++) {
                final String[] operation = scanner.nextLine().split(" ");
                final long a = Long.parseLong(operation[0]), b = Long.parseLong(operation[2]);
                final char operator = operation[1].charAt(0);

                final long result = getOperation(helper, operator).apply(a, b);

                System.out.println(result);
            }
        }

        scanner.close();
    }

    private static BinaryOperator<Long> getOperation(ModularArithmetic helper, char operator) {
        switch(operator) {
            case '+':
                return helper::add;
            case '-':
                return helper::subtract;
            case '*':
                return helper::multiply;
            case '/':
                return helper::divide;
            default:
                throw new IllegalArgumentException();
        }
    }
}
