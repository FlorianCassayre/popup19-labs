package se.kth.popup.lab2.flow;

import java.util.*;

public class FlowSolver {

    private final int n;
    private final int s, t;
    private final long[][] capacities, flows;
    private final long[] height, excess;
    private final int[] seen;

    public FlowSolver(int n, List<Edge> edges, int s, int t) {
        this.n = n;
        this.s = s;
        this.t = t;
        this.capacities = new long[n][n];
        this.flows = new long[n][n];
        this.height = new long[n];
        this.excess = new long[n];
        this.seen = new int[n];

        for(Edge edge : edges) // Initialize capacity matrix
            capacities[edge.u][edge.v] += edge.capacity;
    }

    public Solution maximumFlow() {
        // Great inspiration of: https://en.wikipedia.org/wiki/Push%E2%80%93relabel_maximum_flow_algorithm

        final LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++)
            if(i != s && i != t)
                queue.add(i);

        height[s] = n;
        excess[s] = Long.MAX_VALUE;
        for(int v = 0; v < n; v++)
            push(s, v);

        Iterator<Integer> it = queue.iterator();
        while(it.hasNext()) {
            int u = it.next();
            long oldHeight = height[u];
            discharge(u);
            if(height[u] > oldHeight) {
                queue.addFirst(queue.removeLast()); // FIFO selection rule
                it = queue.iterator();
            }
        }

        return new Solution();
    }

    private void push(int u, int v) {
        final long send = Math.min(excess[u], capacities[u][v] - flows[u][v]);
        flows[u][v] += send;
        flows[v][u] -= send;
        excess[u] -= send;
        excess[v] += send;
    }

    private void relabel(int u) {
        long minHeight = Long.MAX_VALUE;
        for(int v = 0; v < n; v++)
            if(capacities[u][v] - flows[u][v] > 0) {
                minHeight = Math.min(minHeight, height[v]);
                height[u] = minHeight + 1;
            }
    }

    private void discharge(int u) {
        while(excess[u] > 0) {
            if(seen[u] < n) {
                int v = seen[u];
                if(capacities[u][v] - flows[u][v] > 0 && height[u] > height[v])
                    push(u, v);
                else
                    seen[u]++;
            } else {
                relabel(u);
                seen[u] = 0;
            }
        }
    }

    public final class Solution {
        public final long f;
        public final List<Edge> flow;

        private Solution() {
            long f = 0; // Max-flow
            for(int i = 0; i < n; i++)
                f += flows[s][i];
            this.f = f;
            this.flow = new ArrayList<>();
            for(int u = 0; u < n; u++)
                for(int v = 0; v < n; v++)
                    if(flows[u][v] > 0)
                        this.flow.add(new Edge(u, v, flows[u][v]));
        }
    }

    public static final class Edge {
        public final int u, v;
        public final long capacity;

        public Edge(int u, int v, long capacity) {
            this.u = u;
            this.v = v;
            this.capacity = capacity;
        }
    }
}
