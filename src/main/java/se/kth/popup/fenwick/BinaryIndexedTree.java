/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.fenwick;

public class BinaryIndexedTree {

    private final long[] array;

    /**
     * Creates a new Fenwick tree (indexed binary tree) data structure.
     * @param n the size of the array
     */
    public BinaryIndexedTree(int n) {
        this.array = new long[n];
    }

    /**
     * Adds delta to a[i].
     * @param i the index in the array
     * @param delta the number to add
     */
    public void add(int i, long delta) {
        if(i < 0 || i >= array.length)
            throw new IndexOutOfBoundsException(String.valueOf(i));

        i++;
        while(i <= array.length) {
            array[i - 1] += delta;
            i += Integer.lowestOneBit(i);
        }
    }

    /**
     * Computes the prefix sum a[0] + a[1] + ... + a[end - 1].
     * If the index is zero, the result is zero.
     * @param end the end index (exclusive)
     * @return the computed prefix sum
     */
    public long sum(int end) {
        if(end < 0 || end > array.length)
            throw new IndexOutOfBoundsException(String.valueOf(end));

        long sum = 0;
        while(end > 0) {
            sum += array[end - 1];
            end -= Integer.lowestOneBit(end);
        }

        return sum;
    }
}
