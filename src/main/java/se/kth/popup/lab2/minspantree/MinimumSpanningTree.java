/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab2.minspantree;

import se.kth.popup.lab2.MinHeap;

import java.util.*;
import java.util.stream.Collectors;

public final class MinimumSpanningTree {
    private MinimumSpanningTree() {}

    public static Solution mst(int n, List<Edge> edges) {
        // Prim's algorithm with min-heap

        final Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());

        edges.forEach(e -> {
            map.get(e.x).add(e);
            map.get(e.y).add(e);
        });

        final long[] costs = new long[n];
        final int[] prevs = new int[n];
        for(int i = 0; i < costs.length; i++) {
            costs[i] = Long.MAX_VALUE;
            prevs[i] = -1;
        }

        final MinHeap<Integer> queue = new MinHeap<>(Comparator.comparingLong(l -> costs[l]));
        for(int i = 0; i < n; i++)
            queue.add(i);

        while(!queue.isEmpty()) {
            final int v = queue.poll();
            map.get(v).forEach(edge -> {
                final int u = edge.x != v ? edge.x : edge.y;
                if(queue.contains(u) && edge.weight < costs[u]) {
                    prevs[u] = v;
                    costs[u] = edge.weight;
                    queue.decreaseKeyElement(u);
                }
            });
        }

        final List<Edge> tree = edges.stream().filter(e -> prevs[e.x] == e.y || prevs[e.y] == e.x).collect(Collectors.toList());

        return tree.size() == n - 1 ? new Solution(tree) : null;
    }

    public static final class Solution {
        public final List<Edge> edges;
        public final int weight;

        private Solution(List<Edge> edges) {
            Collections.sort(edges);
            this.edges = Collections.unmodifiableList(edges);
            this.weight = edges.stream().map(a -> a.weight).reduce(0, Integer::sum);
        }
    }

    public static final class Edge implements Comparable<Edge> {
        public final int x, y;
        public final int weight;

        public Edge(int x, int y, int weight) {
            this.x = Math.min(x, y);
            this.y = Math.max(x, y);
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(o == null || getClass() != o.getClass())
                return false;
            Edge edge = (Edge) o;
            return x == edge.x && y == edge.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Edge that) {
            if(this.x != that.x)
                return Integer.compare(this.x, that.x);
            else
                return Integer.compare(this.y, that.y);
        }
    }
}
