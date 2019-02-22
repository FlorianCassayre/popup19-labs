package se.kth.popup.lab2.flow;

import se.kth.popup.Kattio;

import java.util.ArrayList;
import java.util.List;

public class MaximumFlowMain {
    public static void run(Kattio kattio) {
        final int n = kattio.getInt(), m = kattio.getInt(), s = kattio.getInt(), t = kattio.getInt();

        final List<FlowSolver.Edge> edges = new ArrayList<>(m);
        for(int i = 0; i < m; i++) {
            final int u = kattio.getInt(), v = kattio.getInt(), c = kattio.getInt();
            edges.add(new FlowSolver.Edge(u, v, c));
        }

        final FlowSolver solver = new FlowSolver(n, edges, s, t);
        final FlowSolver.Solution solution = solver.maximumFlow();

        kattio.println(n + " " + solution.f + " " + solution.flow.size());
        for(FlowSolver.Edge edge : solution.flow)
            kattio.println(edge.u + " " + edge.v + " " + edge.capacity);

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
