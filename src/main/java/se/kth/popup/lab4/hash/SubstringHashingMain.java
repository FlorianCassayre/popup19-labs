/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab4.hash;

import java.util.Scanner;

public class SubstringHashingMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String string = scanner.nextLine();

        final SubstringHash hasher = new SubstringHash(string);

        final int q = scanner.nextInt();
        for(int r = 0; r < q; r++) {
            final int i = scanner.nextInt(), j = scanner.nextInt();

            System.out.println(Long.toUnsignedString(hasher.getHash(i, j)));
        }

        scanner.close();
    }
}
