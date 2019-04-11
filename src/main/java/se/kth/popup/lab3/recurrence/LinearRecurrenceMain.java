/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.recurrence;

import java.util.Scanner;

public class LinearRecurrenceMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt();
        final long[] coefficients = new long[n + 1], initial = new long[n];
        for(int i = 0; i <= n; i++)
            coefficients[i] = scanner.nextLong();
        for(int i = 0; i < n; i++)
            initial[i] = scanner.nextLong();

        final int q = scanner.nextInt();
        for(int i = 0; i < q; i++) {
            final long t = scanner.nextLong(), m = scanner.nextLong();

            final long result = LinearRecurrence.solve(coefficients, initial, t, m);

            System.out.println(result);
        }

        scanner.close();
    }
}
