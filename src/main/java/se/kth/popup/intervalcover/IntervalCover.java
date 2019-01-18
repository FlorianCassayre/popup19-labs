package se.kth.popup.intervalcover;

import se.kth.popup.Kattio;

import java.util.Set;
import java.util.stream.Collectors;

public class IntervalCover {
    public static void run(Kattio kattio) {

        while(kattio.hasMoreTokens()) {
            final Interval interval = getInterval(kattio);
            final Interval[] intervals = new Interval[kattio.getInt()];
            for(int i = 0; i < intervals.length; i++) {
                intervals[i] = getInterval(kattio);
            }

            final Set<Integer> minCover = IntervalUtils.cover(interval, intervals);

            if(!minCover.isEmpty()) {
                kattio.println(minCover.size());
                kattio.println(minCover.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            } else {
                kattio.println("impossible");
            }
        }

        kattio.close();
    }

    private static Interval getInterval(Kattio kattio) {
        return new Interval(kattio.getDouble(), kattio.getDouble());
    }

    public static void main(String[] args) {
        run(new Kattio());
    }
}
