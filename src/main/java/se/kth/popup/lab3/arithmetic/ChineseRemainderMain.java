/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.arithmetic;

import java.util.Scanner;

public class ChineseRemainderMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int t = scanner.nextInt();
        for(int c = 0; c < t; c++) {
            final int a = scanner.nextInt(), n = scanner.nextInt(), b = scanner.nextInt(), m = scanner.nextInt();

            final ChineseRemainder.Solution solution = ChineseRemainder.solve(a, n, b, m);

            System.out.println(solution.x + " " + solution.k);
        }

        scanner.close();
    }
}
