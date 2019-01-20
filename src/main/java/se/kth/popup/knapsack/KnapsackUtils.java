package se.kth.popup.knapsack;

import java.util.Set;

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




        return null; // TODO
    }
}
