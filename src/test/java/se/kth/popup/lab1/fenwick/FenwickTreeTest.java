package se.kth.popup.lab1.fenwick;

import org.junit.jupiter.api.Test;
import se.kth.popup.KattisTester;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeTest extends KattisTester {

    FenwickTreeTest() {
        super("fenwick", FenwickTree::run);
    }

    @Test
    void testRandomOperations() {
        final Random random = new Random(42);

        final int n = 97;

        final BinaryIndexedTree tree = new BinaryIndexedTree(n);

        int[] array = new int[n];
        for(int i = 0; i < 100000; i++) {
            if(random.nextInt(2) > 0) { // Update
                final int index = random.nextInt(array.length), delta = random.nextInt(2001) - 1000;

                array[index] += delta;
                tree.add(index, delta);
            } else { // Query & verify
                final int end = random.nextInt(array.length + 1);

                long sum = 0;
                for(int j = 0; j < end; j++) {
                    sum += array[j];
                }
                final long actual = tree.sum(end);

                assertEquals(sum, actual);
            }
        }

    }

}
