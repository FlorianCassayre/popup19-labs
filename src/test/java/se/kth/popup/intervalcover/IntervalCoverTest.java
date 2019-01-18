package se.kth.popup.intervalcover;

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
        assertEquals(set(0, 1), IntervalUtils.cover(new Interval(0.0, 1.0), new Interval[] { new Interval(-1.0, 0.5), new Interval(0.5, 1.0), new Interval(-100.0, 0.99) }));
    }

    <T> Set<? extends T> set(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
