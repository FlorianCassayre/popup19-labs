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

        Arrays.sort(intervals, Comparator.comparingDouble(i -> i.a)); // Sort the intervals by their lower bound, O(n log n)

        final Set<Integer> set = new HashSet<>();

        if(intervals.length == 0) // Impossible (no intervals to cover with)
            return set;

        // Check if it is possible
        int lastBelow = -1;
        for(int i = 0; i < intervals.length; i++) {
            if(intervals[i].a <= interval.a)
                lastBelow = i;
            else
                break;
        }
        if(lastBelow == -1)
            return set; // Impossible (cannot cover left side)

        double max = 0;
        for(int i = lastBelow; i < intervals.length && max < interval.b; i++) {
            if(i > 0 && intervals[i].a > max)
                break;
            max = Math.max(intervals[i].b, max);
        }
        if(max < interval.b) // Impossible (cannot cover right side)
            return set;

        // TODO actually compute the min. interval cover

        return new HashSet<>(Arrays.asList(0)); // FIXME
    }
}
