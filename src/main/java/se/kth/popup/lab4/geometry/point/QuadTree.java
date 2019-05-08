package se.kth.popup.lab4.geometry.point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QuadTree {
    private final double minX, maxX, minY, maxY;
    private final int depth;
    private QuadTree[] children = null;
    private List<Point> list = new LinkedList<>();

    public QuadTree(double minX, double maxX, double minY, double maxY, int maxDepth) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        this.depth = maxDepth;
    }

    public void insert(Point p) {
        if(depth == 0 || (list != null && list.isEmpty())) {
            list.add(p);
        } else {
            if(list != null) {
                final int d = depth - 1;
                final double xm = (maxX - minX) / 2 + minX, ym = (maxY - minY) / 2 + minY;

                children = new QuadTree[]{new QuadTree(minX, xm, minY, ym, d), new QuadTree(xm, maxX, minY, ym, d), new QuadTree(minX, xm, ym, maxY, d), new QuadTree(xm, maxX, ym, maxY, d)};

                final Point p1 = list.get(0);

                getQuadrant(p1).insert(p1);

                list = null;
            }

            getQuadrant(p).insert(p);
        }
    }

    public Point closest(Point p) {
        return closest(p, Double.POSITIVE_INFINITY).p;
    }

    private Pair closest(Point p, double max) {
        Point best = null;
        if(list != null && !list.isEmpty()) {
            for(Point q : list)
                if(p != q) {
                    final double d = p.distanceSq(q);
                    if(d < max) {
                        max = d;
                        best = q;
                    }
                }
        } else if(list == null) {
            final QuadTree[] array = new QuadTree[children.length];
            System.arraycopy(children, 0, array, 0, children.length);
            Arrays.sort(array, Comparator.comparingDouble(a -> a.distanceTo(p)));

            for(QuadTree tree : array) {
                if(tree.distanceTo(p) < max) {
                    final Pair r = tree.closest(p, max);
                    if(r.d < max) {
                        max = r.d;
                        best = r.p;
                    }
                }
            }
        }

        return new Pair(best, max);
    }

    private QuadTree getQuadrant(Point p) {
        if(p.x < children[0].maxX) {
            if(p.y < children[0].maxY)
                return children[0];
            else
                return children[2];
        } else {
            if(p.y < children[1].maxY)
                return children[1];
            else
                return children[3];
        }
    }

    private double distanceTo(Point p) {
        final double xm = Math.min(Math.max(p.x, minX), maxX), ym = Math.min(Math.max(p.y, minY), maxY);
        return p.distanceSq(new Point(xm, ym));
    }

    public static final class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceSq(Point that) {
            return sq(this.x - that.x) + sq(this.y - that.y);
        }

        private double sq(double x) {
            return x * x;
        }
    }

    public static final class Pair {
        public final Point p;
        public final double d;

        public Pair(Point p, double d) {
            this.p = p;
            this.d = d;
        }
    }
}
