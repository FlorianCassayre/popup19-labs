package se.kth.popup.lab1.intervalcover;

import org.junit.jupiter.api.Test;
import se.kth.popup.KattisTester;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalCoverTest extends KattisTester {

    IntervalCoverTest() {
        super("intervalcover", IntervalCover::run);
    }

    @Test
    void testEdgeCases() {
        assertTrue(IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] {}).isEmpty());
        assertEquals(set(0), IntervalUtils.cover(new Interval(0.0, 0.0), new Interval[] { new Interval(-1.0, 1.0) }));
        assertEquals(set(), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(-1.0, 0.5), new Interval(0.4, 0.6) }));
        assertEquals(set(2), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] {
                new Interval(-100.0, -99.0), new Interval(-2.0, 0.5), new Interval(-1.0, 1.0), new Interval(0.5, 2.0), new Interval(-0.9, 0.9), new Interval(99.0, 100.0)
        }));
        assertEquals(set(), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(-100.0, 0.0) }));
        assertEquals(set(), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(-100.0, 0.5) }));
        assertEquals(set(0), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(-100.0, 1.0) }));
        assertEquals(set(), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(0.5, 100.0) }));
        assertEquals(set(0), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(0.0, 100.0) }));
        assertEquals(set(1, 2), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(0.1, 0.5), new Interval(0.5, 1.0), new Interval(-100.0, 0.99) }));

        assertEquals(set(3, 5, 7), IntervalUtils.cover(new Interval(10, 20), new Interval[] {
                new Interval(21, 25), new Interval(23, 30),
                new Interval(2, 10), new Interval(4, 13), new Interval(3, 12),
                new Interval(11, 15), new Interval(13, 14), new Interval(15, 20), new Interval(16, 28),
                new Interval(0, 5), new Interval(2, 7), new Interval(1, 8),
        }));

        assertEquals(set(), IntervalUtils.cover(new Interval(10, 20), new Interval[] {
                new Interval(21, 25), new Interval(23, 30),
                new Interval(2, 10), new Interval(4, 13), new Interval(3, 12),
                new Interval(11, 15), new Interval(13, 14), new Interval(16, 20), new Interval(16, 28),
                new Interval(0, 5), new Interval(2, 7), new Interval(1, 8),
        }));

        assertEquals(set(), IntervalUtils.cover(new Interval(10, 20), new Interval[] {
                new Interval(24, 29), new Interval(25, 30), new Interval(0, 5), new Interval(2, 12), new Interval(11, 18),
        }));

        assertEquals(set(), IntervalUtils.cover(new Interval(10, 20), new Interval[] {
                new Interval(24, 29), new Interval(25, 30), new Interval(21, 22), new Interval(15, 21), new Interval(11, 18),
        }));

        assertEquals(set(2), IntervalUtils.cover(new Interval(0.0, 0.0), new Interval[] { new Interval(-1.0, -0.1), new Interval(-200, -100), new Interval(-0.01, 0.0), new Interval(-0.01, -0.001) }));
    }

    <T> Set<? extends T> set(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
