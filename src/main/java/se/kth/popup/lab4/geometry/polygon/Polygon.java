package se.kth.popup.lab4.geometry.polygon;

public class Polygon {
    private final Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }

    /**
     * Computes the area of that polygon.
     * @return the value of the area
     */
    public double getAlgebraicArea() {
        double acc = 0.0;

        final Point a = points[0];
        for(int i = 1; i < points.length - 1; i++) {
            final Point b = points[i], c = points[i + 1];

            // Area of each sub-triangle
            final double v = a.x * b.y + b.x * c.y + c.x * a.y - a.y * b.x - b.y * c.x - c.y * a.x;
            acc += v / 2.0;
        }

        return acc;
    }

    public double getArea() {
        return Math.abs(getAlgebraicArea());
    }

    public static final class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
