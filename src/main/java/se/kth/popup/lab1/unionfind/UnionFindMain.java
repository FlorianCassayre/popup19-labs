/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab1.unionfind;

import se.kth.popup.Kattio;

public class UnionFindMain {
    public static void run(Kattio kattio) {
        final int n = kattio.getInt(), q = kattio.getInt();

        final UnionFind forest = new UnionFind(n);

        for(int i = 0; i < q; i++) {
            final String op = kattio.getWord();
            final int a = kattio.getInt(), b = kattio.getInt();
            switch(op) {
                case "=":
                    forest.union(a, b);
                    break;
                case "?":
                    kattio.println(forest.same(a, b) ? "yes" : "no");
                    break;
                default:
                    throw new IllegalStateException();
            }
        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
