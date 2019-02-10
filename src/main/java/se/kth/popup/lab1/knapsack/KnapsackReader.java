/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab1.knapsack;

import se.kth.popup.Kattio;
import java.util.List;


public class KnapsackReader
{
    public static void run(Kattio kattio)
    {
        while (kattio.hasMoreTokens())
        {
            int capacity = (int) kattio.getDouble();
            int n_items = kattio.getInt();
            int[] values = new int[n_items];
            int[] weights = new int[n_items];

            for(int i = 0; i < n_items; i++)
            {
                values[i] = kattio.getInt();
                weights[i] = kattio.getInt();
            }

            Knapsack knapsack = new Knapsack(capacity, values, weights);
            List<Integer> items_chosen = knapsack.solve();

            kattio.println(items_chosen.size());
            for (int i = 0; i < items_chosen.size(); i++)
            {
            	kattio.print(items_chosen.get(i)-1 + " ");
            }

            kattio.println();
        }
        kattio.close();
    }

    public static void main(String[] args)
    {
        run(new Kattio());
    }
}