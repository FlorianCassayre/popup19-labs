/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.intervalcover;

import java.util.*;
import java.util.stream.Collectors;

public final class IntervalUtils {
    private IntervalUtils() {}

    /**
     * Computes the minimum cover of an interval.
     * @param interval the interval to be covered
     * @param intervals the interval to cover with
     * @return the indices of the interval forming a minimum cover
     */
    public static Set<Integer> cover(Interval interval, Interval[] intervals) {

        final Integer[] indices = new Integer[intervals.length];
        for(int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, Comparator.comparingDouble(i -> intervals[i].a)); // Sort the intervals by their lower bound, O(n log n)
        final Interval[] sorted = new Interval[intervals.length];
        for(int i = 0; i < sorted.length; i++)
            sorted[i] = intervals[indices[i]];

        final Set<Integer> set = new HashSet<>();

        if(sorted.length == 0) // Impossible (no intervals to cover with)
            return set;

        // Check if it is possible
        int largestLeft = -1;
        for(int i = 0; i < sorted.length && sorted[i].a <= interval.a; i++) {
            if(sorted[i].b >= interval.a && (largestLeft == -1 || sorted[i].b > sorted[largestLeft].b)) {
                largestLeft = i;
            }
        }
        if(largestLeft == -1)
            return set; // Impossible (cannot cover left side)

        set.add(largestLeft);

        while(largestLeft < sorted.length && sorted[largestLeft].b < interval.b) {
            int bestNext = -1;
            for(int j = largestLeft + 1; j < sorted.length && sorted[j].a <= sorted[largestLeft].b; j++) {
                if(sorted[j].b > sorted[largestLeft].b && (bestNext == -1 || sorted[j].b > sorted[bestNext].b))
                    bestNext = j;
            }
            if(bestNext == -1)
                return new HashSet<>();

            set.add(bestNext);
            largestLeft = bestNext;
        }

        if(sorted[largestLeft].b < interval.b) // Impossible (cannot cover right side)
            return new HashSet<>();

        return set.stream().map(i -> indices[i]).collect(Collectors.toSet());
    }
}
