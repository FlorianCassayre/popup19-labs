/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab3.arithmetic;

public class ModularArithmetic {
    public final long n;

    /**
     * Construct a new modular group of order <code>n</code>.
     * @param n the order of the group
     */
    public ModularArithmetic(long n) {
        if(n <= 0)
            throw new IllegalArgumentException();

        this.n = n;
    }

    /**
     * Adds two numbers in this group.
     * @param a the first number
     * @param b the second number
     * @return the result of the addition
     */
    public long add(long a, long b) {
        return normalize(a + b);
    }

    /**
     * Subtracts two numbers in this group.
     * @param a the first number
     * @param b the second number
     * @return the result of the subtraction
     */
    public long subtract(long a, long b) {
        return normalize(a - b);
    }

    /**
     * Multiplies two numbers in this group.
     * @param a the first number
     * @param b the second number
     * @return the result of the multiplication
     */
    public long multiply(long a, long b) {
        // The main challenge here is to prevent overflow

        a = normalize(a);
        b = normalize(b);

        long acc = 0;
        while(b > 0) {
            if((b & 1) != 0)
                acc = (acc + a) % n;
            a = (a << 1) % n;
            b >>= 1;
        }

        return acc;
    }

    /**
     * Divides two numbers in this group, if possible.
     * In other words, multiplies <code>a</code> by the modular inverse of <code>b</code>.
     * @param a the first number
     * @param b the second number
     * @return the result of the division if it exists, or <code>-1</code> otherwise
     */
    public long divide(long a, long b) {
        final long inv = inverse(b);
        if(inv != -1)
            return multiply(a, inv);
        else
            return inv;
    }

    /**
     * Computes the modular inverse of a number in this group, if possible.
     * @param a the number to invert
     * @return the modular inverse if it exists, <code>-1</code> otherwise
     */
    public long inverse(long a) {
        // Extended Euclidean algorithm

        a = normalize(a);

        long t = 0, nt = 1, r = n, nr = a;

        while(nr != 0) {
            final long q = r / nr, pt = nt, pr = nr;
            nt = t - q * nt;
            t = pt;
            nr = r - q * nr;
            r = pr;
        }

        if(r > 1) // Inverse does not exist
            return -1;

        if(t < 0)
            t += n;
        return t;
    }

    /**
     * Normalizes a number in this group.
     * @param a the number to normalize
     * @return a number between <code>0</code> (included) and <code>n</code> (excluded)
     */
    public long normalize(long a) {
        return ((a % n) + n) % n;
    }
}
