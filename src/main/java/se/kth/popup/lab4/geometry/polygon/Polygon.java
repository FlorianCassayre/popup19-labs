package se.kth.popup.lab4.geometry.polygon;

import se.kth.popup.lab4.geometry.Vector;

public class Polygon {
    private final Vector[] points;

    public Polygon(Vector[] points) {
        this.points = points;
    }

    /**
     * Computes the algebraic area (depending on the orientation) of that polygon.
     * @return the value of the area
     */
    public double getAlgebraicArea() {
        double acc = 0.0;

        final Vector a = points[0];
        for(int i = 1; i < points.length - 1; i++) {
            final Vector b = points[i], c = points[i + 1];

            // Area of each sub-triangle
            final double v = a.x * b.y + b.x * c.y + c.x * a.y - a.y * b.x - b.y * c.x - c.y * a.x;
            acc += v / 2.0;
        }

        return acc;
    }

    /**
     * Computes the absolute area of that polygon.
     * @return the value of the area
     */
    public double getArea() {
        return Math.abs(getAlgebraicArea());
    }

    /**
     * Determines if a point if inside, outside or on the boundary of that polygon.
     * @param point the point to check for
     * @return <code>-1</code> if outside, <code>0</code> if on boundary, <code>1</code> if inside
     */
    public int isInside(Vector point) {
        double angle = 0.0;
        for(int i = 0; i < points.length; i++) {
            final Vector p = points[i], q = points[(i + 1) % points.length];
            final Vector u = point.subtract(p), v = q.subtract(p);
            final long dot = u.dot(v);
            if(u.cross(v) == 0 && dot >= 0 && dot <= v.dot(v)) // Collinear + Included => Boundary
                return 0;
            final double a = p.subtract(point).angle(q.subtract(point));
            angle += a;
        }
        return Math.abs(angle) > Math.PI ? 1 : -1;
    }
}
