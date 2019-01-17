/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.fenwick;

import se.kth.popup.Kattio;

public class FenwickTree {

    public static void run(Kattio kattio) {

        final int n = kattio.getInt(), q = kattio.getInt();

        final BinaryIndexedTree tree = new BinaryIndexedTree(n);

        for(int i = 0; i < q; i++) {
            final String op = kattio.getWord();
            if(op.equals("+")) {
                final int index = kattio.getInt();
                final long delta = (long) kattio.getInt();

                tree.add(index, delta);

            } else if(op.equals("?")) {
                final int end = kattio.getInt();

                kattio.println(tree.sum(end));
            }
        }

        kattio.close();
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
