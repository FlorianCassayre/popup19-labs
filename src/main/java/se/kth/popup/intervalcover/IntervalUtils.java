/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.intervalcover;

import java.util.*;

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
        Arrays.sort(intervals, Comparator.comparingDouble(i -> i.a)); // Sort the intervals by their lower bound, O(n log n)

        final Set<Integer> set = new HashSet<>();

        if(intervals.length == 0) // Impossible (no intervals to cover with)
            return set;

        // Check if it is possible
        double largestLeft = 0, largestRight = 0;
        int largestLeftIndex = -1, largestRightIndex = -1;
        for(int i = 0; i < intervals.length && intervals[i].a <= interval.a; i++) {
            if(intervals[i].b >= interval.a && (largestLeftIndex == -1 || intervals[i].b > largestLeft)) {
                largestLeft = intervals[i].b;
                largestLeftIndex = i;
            }
        }
        if(largestLeftIndex == -1)
            return set; // Impossible (cannot cover left side)

        for(int i = intervals.length - 1; i >= 0 && intervals[i].b >= interval.b; i--) {
            if(intervals[i].b >= interval.b && (largestRightIndex == -1 || intervals[i].a < largestRight)) {
                largestRight = intervals[i].b;
                largestRightIndex = i;
            }
        }
        if(largestRightIndex == -1)
            return set; // Impossible (cannot cover right side)

        int index = largestLeftIndex, start = largestLeftIndex + 1;
        set.add(largestLeftIndex);
        while(intervals[index].b < interval.b) {
            double bestValue = 0;
            int bestIndex = -1;

            for(int i = start; i < intervals.length && intervals[i].a <= intervals[index].b; i++) {
                start = i + 1;
                if(intervals[i].b >= intervals[index].a && (bestIndex == -1 || intervals[i].b > bestValue)) {
                    bestValue = intervals[i].b;
                    bestIndex = i;
                }
            }

            if(bestIndex == -1) // Impossible (no intervals were found)
                return new HashSet<>();

            set.add(bestIndex);
            index = bestIndex;
        }

        return set;
    }
}
