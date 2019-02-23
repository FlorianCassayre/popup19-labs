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
        for(Map.Entry<Integer, ArrayDeque<Integer>> entry : edges.entrySet()) {
            degree[entry.getKey()] -= entry.getValue().size();
            for(Integer to : entry.getValue())
                degree[to]++;
        }

        int pos = -1, neg = -1;
        for(int i = 0; i < n; i++) { // Check the degree of each vertex
            final int deg = degree[i];
            if(deg != 0){
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
        // TODO self loops
        PathNode last = null;
        final Map<Integer, PathNode> cycles = new HashMap<>();
        for(int u = 0; u < n; u++) {
            final ArrayDeque<Integer> list = edges.get(u);
            while(!list.isEmpty()) {
                PathNode path = mergeCycles(new PathNode(u), cycles);
                last = path;
                final PathNode head = path;
                int v;
                while(!edges.get(path.vertex).isEmpty() && (v = edges.get(path.vertex).poll()) != u) {
                    final PathNode next = new PathNode(v);
                    next.previous = path;
                    path.next = next;
                    path = mergeCycles(next, cycles);
                }
                // Close the cycle
                path.next = head;
                head.previous = path;
                // Add it to the list of cycles
                PathNode current = head;
                do {
                    cycles.put(current.vertex, current);
                    current = current.next;
                } while(current != head);
            }
        }

        if(last != null)
            removeCycle(last, cycles);

        // If cycles are not empty at this point, the graph has several connected components (therefore impossible)
        if(!cycles.isEmpty())
            return null;

        final PathNode head = last;
        if(flag) { // Start from the excessive source (if there is such a source)
            while(last.previous.vertex != pos || last.vertex != neg)
                last = last.next;
        }
        final List<Integer> solution = new ArrayList<>();
        solution.add(last.vertex);
        do {
            last = last.next;
            solution.add(last.vertex);
        } while(last.next != null && head != last);

        if(flag)
            solution.remove(solution.size() - 1);

        return solution;
    }

    private static void removeCycle(PathNode cycle, Map<Integer, PathNode> cycles) {
        if(cycle == null)
            throw new NullPointerException();
        PathNode temp = cycle;
        do {
            cycles.remove(temp.vertex);
            temp = temp.next;
        } while(temp != cycle);
    }

    private static PathNode mergeCycles(PathNode node, Map<Integer, PathNode> cycles) {
        final PathNode cycle = cycles.get(node.vertex);
        if(cycle != null) {
            final PathNode to = cycle.next;

            removeCycle(cycle, cycles);

            node.next = to;
            to.previous = node;
            cycle.next = null;

            return cycle;
        } else {
            return node;
        }
    }

    public static final class PathNode {
        public final int vertex;
        public PathNode previous, next;

        public PathNode(int vertex) {
            this.vertex = vertex;
        }
    }
}
