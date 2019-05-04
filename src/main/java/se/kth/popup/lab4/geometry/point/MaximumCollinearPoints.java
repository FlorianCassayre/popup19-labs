package se.kth.popup.lab4.geometry.point;

import se.kth.popup.lab4.geometry.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MaximumCollinearPoints {
    private MaximumCollinearPoints() {}

    public static int find(Vector[] points) {
        final Map<Pair, Integer> map = new HashMap<>();
        int max = 0;

        for(int i = 0; i < points.length; i++) {
            final Vector a = points[i];
            for(int j = i + 1; j < points.length; j++) {
                final Vector b = points[j];
                final Vector diff = b.subtract(a);

                final Vector direction, point;
                if(diff.x == 0) {
                    direction = new Vector(0, 1);
                    point = new Vector(a.x, 0);
                } else if(diff.y == 0) {
                    direction = new Vector(1, 0);
                    point = new Vector(0, a.y);
                } else {
                    final long d = gcd(diff.x, diff.y), sign = diff.x < 0 ? -1 : 1;
                    direction = new Vector(diff.x / d * sign, diff.y / d * sign);
                    final long reduced = ((a.x % direction.x) + direction.x) % direction.x;
                    point = new Vector(reduced, a.y - ((a.x - reduced) / direction.x) * direction.y);
                }
                final Pair pair = new Pair(direction, point);

                final int v = map.merge(pair, 1, (p, q) -> p + q);
                max = Math.max(v, max);
            }
        }

        return (int) Math.round(-0.5 + Math.sqrt(0.25 + 2 * max)) + 1;
    }

    private static long gcd(long a, long b) {
        long max = Math.abs(a), min = Math.abs(b);

        while (max > 0) {
            if (max < min) {
                long x = max;
                max = min;
                min = x;
            }
            max %= min;
        }

        return min;
    }

    private static final class Pair {
        public final Vector a, b;

        private Pair(Vector a, Vector b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(o == null || getClass() != o.getClass())
                return false;
            Pair pair = (Pair) o;
            return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
