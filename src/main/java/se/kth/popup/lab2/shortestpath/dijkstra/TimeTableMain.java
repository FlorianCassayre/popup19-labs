/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */

package se.kth.popup.lab2.shortestpath.dijkstra;

import se.kth.popup.Kattio;
import java.util.ArrayList;
import java.util.List;
import se.kth.popup.lab2.Edge;


public class TimeTableMain
{
    public static void run(Kattio kattio)
    {
        while (true)
        {
            final int n = kattio.getInt(), m = kattio.getInt(), q = kattio.getInt(), s = kattio.getInt();
            if (n == 0) break;
            final List<Edge> edges = new ArrayList<>(m);
            for(int i = 0; i < m; i++)
            {
                edges.add(new Edge(kattio.getInt(), kattio.getInt(), kattio.getInt(), kattio.getInt(), kattio.getInt()));
            }
            final Solution solution = Dijkstra.shortestPath(n, edges, s);
            for(int i = 0; i < q; i++)
            {
                final int distance = solution.distances[kattio.getInt()];
                if (distance == Integer.MAX_VALUE)
                {
                    kattio.println("Impossible");
                }
                else
                {
                    kattio.println(distance);
                }
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

