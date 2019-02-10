/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab1.intervalcover;

public final class Interval {
    public final double a, b;

    /**
     * Creates a new real interval.
     * @param a the lower bound
     * @param b the upper bound
     */
    public Interval(double a, double b) {
        assert a <= b;

        this.a = a;
        this.b = b;
    }
}
