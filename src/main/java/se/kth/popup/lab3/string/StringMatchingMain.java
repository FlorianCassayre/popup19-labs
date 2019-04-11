/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.string;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringMatchingMain {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            final String pattern = scanner.nextLine(), text = scanner.nextLine();

            final List<Integer> matches = SubstringSearch.find(text, pattern);

            System.out.println(matches.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        scanner.close();
    }
}
