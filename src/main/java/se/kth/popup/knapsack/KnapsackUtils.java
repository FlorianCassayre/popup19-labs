/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.knapsack;

import java.util.*;

public final class KnapsackUtils {
    private KnapsackUtils() {}

    /**
     * Finds an optimal solution to the knapsack problem;
     * namely it maximises the total value with upper bounded weight.
     * @param capacity the capacity in terms of weight
     * @param values the values of the items
     * @param weights the weights of the items
     * @return a set of the chosen items
     */
    public static Set<Integer> knapsack(int capacity, int[] values, int[] weights) {
        if(values.length != weights.length)
            throw new IllegalArgumentException();

        final int n = values.length;

        final List<Set<Integer>> solutions = new ArrayList<>(capacity + 1);
        final int[] solutionsValues = new int[capacity + 1];

        // Base case
        solutions.add(new HashSet<>());
        solutionsValues[0] = 0;

        for(int c = 1; c <= capacity; c++) {

            Set<Integer> bestSet = solutions.get(c - 1);
            int bestValue = solutionsValues[c - 1];

            for(int i = 0; i < n; i++) {
                final int previousWeight = c - weights[i];
                if(previousWeight >= 0) { // Item can enter a knapsack of capacity `c`
                    final Set<Integer> set = solutions.get(previousWeight);
                    if(!set.contains(i)) { // This knapsack does not contain the item, yet
                        final int value = solutionsValues[previousWeight] + values[i];
                        if(value > bestValue) { // FIXME potentially greedy!!
                            bestSet = new HashSet<>(set);
                            bestSet.add(i);

                            bestValue = value;
                        }
                    }
                }
            }

            solutions.add(bestSet);
            solutionsValues[c] = bestValue;
        }

        return solutions.get(capacity);
    }
}
