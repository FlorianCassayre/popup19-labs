package se.kth.popup.intervalcover;

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
