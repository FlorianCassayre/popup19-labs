/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.eulerianpath;

import java.util.*;

public final class EulerianPath {
    private EulerianPath() {}

    /**
     * Computes a Eulerian path of a graph if it exists.
     * @param n the number of vertices
     * @param edges the edges of this graph
     * @return the order in which the vertices can be visited, or <code>null</code>
     */
    public static List<Integer> findPath(int n, Map<Integer, ArrayDeque<Integer>> edges) {
        final int[] degree = new int[n];
        int any = -1;
        int edgesCount = 0;
        for(Map.Entry<Integer, ArrayDeque<Integer>> entry : edges.entrySet()) {
            any = entry.getKey();
            degree[entry.getKey()] -= entry.getValue().size();
            edgesCount += entry.getValue().size();
            for(Integer to : entry.getValue())
                degree[to]++;
        }

        int pos = -1, neg = -1;
        for(int i = 0; i < n; i++) { // Check the degree of each vertex
            final int deg = degree[i];
            if(deg != 0) {
                if(deg == 1 && pos == -1)
                    pos = i;
                else if(deg == -1 && neg == -1)
                    neg = i;
                else
                    return null;
            }
        }

        boolean flag = false;
        if(pos != -1 && neg != -1) {
            edges.get(pos).add(neg);
            flag = true;
        } else if(pos != -1 || neg != -1) {
            return null;
        }

        final Deque<Integer> head = new ArrayDeque<>(), tail = new ArrayDeque<>();
        head.push(any);

        while(!head.isEmpty()) {
            while(!edges.get(head.peek()).isEmpty()) {
                final int u = head.peek();
                final int v = edges.get(u).pop();
                head.push(v);
            }

            while(!head.isEmpty() && edges.get(head.peek()).isEmpty()) {
                tail.push(head.pop());
            }
        }

        final List<Integer> temp = new ArrayList<>(tail);
        final List<Integer> fixed;

        if(flag) {
            fixed = new ArrayList<>(temp.size() - 1);
            int index = -1;
            for(int i = 0; i < temp.size(); i++) {
                if(temp.get(i) == pos && temp.get(i < temp.size() - 1 ? i + 1 : 0) == neg) {
                    index = i;
                    break;
                }
            }
            if(index == -1)
                return null;
            for(int i = index + 1; i < temp.size() - 1; i++) {
                fixed.add(temp.get(i));
            }
            for(int i = 0; i <= index; i++) {
                fixed.add(temp.get(i));
            }
        } else {
            fixed = new ArrayList<>(tail);
        }

        return fixed.size() == edgesCount + 1 ? fixed : null;
    }
}
