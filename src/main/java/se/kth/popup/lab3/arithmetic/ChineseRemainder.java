package se.kth.popup.lab3.arithmetic;

public final class ChineseRemainder {
    private ChineseRemainder() {}

    public static Solution solve(int a, int n, int b, int m) {

        final long v1 = new ModularArithmetic(n).inverse(m), v2 = new ModularArithmetic(m).inverse(n);

        final ModularArithmetic ring = new ModularArithmetic((long) n * m);

        // x = a * v1 * m + b * v2 * n
        final long x = ring.add(ring.multiply(ring.multiply(a, v1), m), ring.multiply(ring.multiply(b, v2), n));

        return new Solution(x, ring.n);
    }

    public static final class Solution {
        public final long x, k;

        private Solution(long x, long k) {
            this.x = x;
            this.k = k;
        }
    }
}
