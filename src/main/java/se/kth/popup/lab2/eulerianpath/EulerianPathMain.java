/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.eulerianpath;

import se.kth.popup.Kattio;

import java.util.*;
import java.util.stream.Collectors;

public class EulerianPathMain {
    public static void run(Kattio kattio) {
        while(true) {
            final int n = kattio.getInt(), m = kattio.getInt();

            if(n == 0 && m == 0)
                break;

            final Map<Integer, ArrayDeque<Integer>> edges = new HashMap<>(m);
            for(int i = 0; i < n; i++) {
                edges.put(i, new ArrayDeque<>());
            }
            for(int i = 0; i < m; i++) {
                final int from = kattio.getInt(), to = kattio.getInt();
                if(!edges.containsKey(from))
                    edges.put(from, new ArrayDeque<>());
                edges.get(from).add(to);
            }

            final List<Integer> solution = EulerianPath.findPath(n, edges);

            if(solution != null) {
                kattio.println(solution.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            } else {
                kattio.println("Impossible");
            }
        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
