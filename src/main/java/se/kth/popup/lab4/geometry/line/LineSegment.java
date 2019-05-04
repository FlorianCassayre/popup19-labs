package se.kth.popup.lab4.geometry.line;

import se.kth.popup.lab4.geometry.Vector;

import java.util.Arrays;
import java.util.Comparator;

public class LineSegment {
    private static final Comparator<Vector> COMPARATOR = (a, b) -> {
        if(a.x != b.x)
            return Long.compare(a.x, b.x);
        else
            return Long.compare(a.y, b.y);
    };

    public final Vector a, b;
    private final long lx, ly, lz;

    public LineSegment(Vector a, Vector b) {
        final Vector[] points = {a, b};
        Arrays.sort(points, COMPARATOR);

        this.a = points[0];
        this.b = points[1];

        this.lx = b.y - a.y;
        this.ly = a.x - b.x;
        this.lz = -(lx * a.x + ly * a.y);
    }

    public boolean isPoint() {
        return a.equals(b);
    }

    public Result intersection(LineSegment that) {
        // 3D cross product
        final long cx = this.ly * that.lz - this.lz * that.ly, cy = this.lz * that.lx - this.lx * that.lz, cz = this.lx * that.ly - this.ly * that.lx;

        if(cz == 0) { // Lines are parallel or one of them is a point
            final boolean ap = this.isPoint(), bp = that.isPoint();
            final LineSegment la = ap ? that : this, lb = ap ? this : that;
            if(ap && bp) { // Both segments are points
                if(this.a.equals(that.a))
                    return new SingleIntersection(this.a.x, this.a.y);
                else
                    return new NoIntersection();
            } else if(la.lx * lb.a.x + la.ly * lb.a.y + la.lz == 0) { // Lines are on the same axis
                final Vector[] points = {this.a, this.b, that.a, that.b};
                Arrays.sort(points, COMPARATOR);

                if(points[1].equals(points[2])) {
                    return new SingleIntersection(points[1].x, points[2].y); // Intersection is a single point
                } else if(points[1] == this.b && points[2] == that.a || points[1] == that.b && points[2] == this.a) {
                    return new NoIntersection(); // Segments are disjoint
                } else {
                    return new InfiniteIntersection(points[1].x, points[1].y, points[2].x, points[2].y); // Intersection is a segment
                }
            } else {
                return new NoIntersection();
            }
        } else { // Lines are non parallel
            final double x = (double) cx / cz, y = (double) cy / cz;

            if(this.inside(x, y) && that.inside(x, y))
                return new SingleIntersection(x, y); // Non parallel single point intersection
            else
                return new NoIntersection(); // Lines intersect but not the segments
        }
    }

    private boolean inside(double x, double y) {
        return x >= Math.min(a.x, b.x) && x <= Math.max(a.x, b.x) && y >= Math.min(a.y, b.y) && y <= Math.max(a.y, b.y);
    }

    public abstract class Result {
    }

    public final class NoIntersection extends Result {
    }

    public final class SingleIntersection extends Result {
        public final double x, y;

        private SingleIntersection(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public final class InfiniteIntersection extends Result {
        public final double x1, y1, x2, y2;

        private InfiniteIntersection(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
