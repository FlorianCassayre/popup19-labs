/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.knapsack;

import se.kth.popup.Kattio;

import java.util.Set;
import java.util.stream.Collectors;

public class KnapsackMain {

    public static void run(Kattio kattio) {

        while(kattio.hasMoreTokens()) {
            final int capacity = (int) kattio.getDouble();
            final int n = kattio.getInt();

            final int[] values = new int[n], weights = new int[n];

            for(int i = 0; i < n; i++) {
                values[i] = kattio.getInt();
                weights[i] = kattio.getInt();
            }

            final Set<Integer> set = KnapsackUtils.knapsack(capacity, values, weights);

            kattio.println(set.size());
            kattio.println(set.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        }


        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
