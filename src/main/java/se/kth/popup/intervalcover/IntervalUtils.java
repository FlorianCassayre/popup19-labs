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

        // FIXME preserve the indices before sorting!
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
        double largestLeft = 0, largestRight = 0;
        int largestLeftIndex = -1, largestRightIndex = -1;
        for(int i = 0; i < sorted.length && sorted[i].a <= interval.a; i++) {
            if(sorted[i].b >= interval.a && (largestLeftIndex == -1 || sorted[i].b > largestLeft)) {
                largestLeft = sorted[i].b;
                largestLeftIndex = i;
            }
        }
        if(largestLeftIndex == -1)
            return set; // Impossible (cannot cover left side)

        for(int i = sorted.length - 1; i >= 0 && sorted[i].b >= interval.b; i--) {
            if(sorted[i].b >= interval.b && (largestRightIndex == -1 || sorted[i].a < largestRight)) {
                largestRight = sorted[i].b;
                largestRightIndex = i;
            }
        }
        if(largestRightIndex == -1)
            return set; // Impossible (cannot cover right side)

        int index = largestLeftIndex, start = largestLeftIndex + 1;
        set.add(largestLeftIndex);
        while(sorted[index].b < interval.b) {
            double bestValue = 0;
            int bestIndex = -1;

            for(int i = start; i < sorted.length && sorted[i].a <= sorted[index].b; i++) {
                start = i + 1;
                if(sorted[i].b >= sorted[index].a && (bestIndex == -1 || sorted[i].b > bestValue)) {
                    bestValue = sorted[i].b;
                    bestIndex = i;
                }
            }

            if(bestIndex == -1) // Impossible (no intervals were found)
                return new HashSet<>();

            set.add(bestIndex);
            index = bestIndex;
        }


        return set.stream().map(i -> indices[i]).collect(Collectors.toSet());
    }
}
