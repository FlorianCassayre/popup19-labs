/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.string;

import java.util.ArrayList;
import java.util.List;

public final class SubstringSearch {
    private SubstringSearch() {}

    /**
     * Efficiently finds all occurrences of a string in another string.
     * @param text the haystack
     * @param pattern the needle
     * @return all indices where the pattern starts
     */
    public static List<Integer> find(String text, String pattern) {
        // Knuth–Morris–Pratt algorithm

        final List<Integer> occurrences = new ArrayList<>();
        final int[] table = buildTable(pattern);

        int j = 0, k = 0;
        while(j < text.length()) {
            if(pattern.charAt(k) == text.charAt(j)) {
                j++;
                k++;
                if(k == pattern.length()) {
                    occurrences.add(j - k);
                    k = table[k];
                }
            } else {
                k = table[k];
                if(k < 0) {
                    j++;
                    k++;
                }
            }
        }

        return occurrences;
    }

    private static int[] buildTable(String pattern) {
        // KMP requires a table of partial matches

        final int[] table = new int[pattern.length() + 1];

        int pos = 1, cnd = 0;

        table[0] = -1;

        while(pos < pattern.length()) {
            if(pattern.charAt(pos) == pattern.charAt(cnd))
                table[pos] = table[cnd];
            else {
                table[pos] = cnd;
                cnd = table[cnd];
                while(cnd >= 0 && pattern.charAt(pos) != pattern.charAt(cnd))
                    cnd = table[cnd];
            }
            pos++;
            cnd++;
        }
        table[pos] = cnd;

        return table;
    }

    /**
     * Efficiently finds all occurrences of strings in another string.
     * @param text the haystack
     * @param patterns the needles
     * @return a list of lists of all indices where the patterns start
     */
    public static List<List<Integer>> find(String text, String[] patterns) {
        return null; // TODO
    }
}
