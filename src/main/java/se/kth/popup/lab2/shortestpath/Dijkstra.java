package se.kth.popup.lab2.shortestpath;

import se.kth.popup.lab2.MinHeap;

import java.util.*;

public final class Dijkstra {
    private Dijkstra() {}

    public static Solution shortestPath(int n, List<Edge> edges, int s) {
        final Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());

        edges.forEach(e -> {
            map.get(e.u).add(e);
        });

        final int[] distances = new int[n];
        final int[] parents = new int[n];
        for(int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        final MinHeap<Integer> queue = new MinHeap<>(Comparator.comparingLong(l -> distances[l]));
        for(int i = 0; i < n; i++)
            queue.add(i);

        distances[s] = 0;

        while(!queue.isEmpty()) {
            final int u = queue.poll();
            map.get(u).forEach(edge -> {
                final int v = edge.v;
                if(distances[u] < Integer.MAX_VALUE && distances[v] > distances[u] + edge.weight) {
                    distances[v] = distances[u] + edge.weight;
                    parents[v] = u;
                    queue.decreaseKeyElement(v);
                }
            });
        }

        return new Solution(n, distances, parents);
    }

    public static final class Solution {
        public final int n;
        public final int[] distances, parents;

        private Solution(int n, int[] distances, int[] parents) {
            this.n = n;
            this.distances = distances;
            this.parents = parents;
        }
    }

    public static final class Edge {
        public final int u, v;
        public final int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
