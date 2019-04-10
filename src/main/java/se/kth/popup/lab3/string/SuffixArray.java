package se.kth.popup.lab3.string;

import java.util.Arrays;

public final class SuffixArray {
    private final int[] array;

    /**
     * Constructs the suffix array for that string.
     * @param string the string
     */
    public SuffixArray(String string) {
        final int n = string.length();

        this.array = new int[n];

        final Suffix[] suffixes = new Suffix[n];

        for(int i = 0; i < n; i++) {
            final Suffix suffix = new Suffix();
            suffix.index = i;
            suffix.rank0 = string.charAt(i);
            suffix.rank1 = i + 1 < n ? string.charAt(i + 1) : -1;
            suffixes[i] = suffix;
        }

        Arrays.sort(suffixes);

        final int[] indices = new int[n];

        for(int k = 4; k < 2 * n; k <<= 1) {
            int rank = 0;
            int prevRank = suffixes[0].rank0;
            suffixes[0].rank0 = rank;
            indices[suffixes[0].index] = 0;

            for(int i = 1; i < n; i++) {
                if(suffixes[i].rank0 == prevRank && suffixes[i].rank1 == suffixes[i - 1].rank1) {
                    prevRank = suffixes[i].rank0;
                    suffixes[i].rank0 = rank;
                } else {
                    prevRank = suffixes[i].rank0;
                    rank++;
                    suffixes[i].rank0 = rank;
                }
                indices[suffixes[i].index] = i;
            }

            for(int i = 0; i < n; i++) {
                final int nextIndex = suffixes[i].index + (k >> 1);
                suffixes[i].rank1 = nextIndex < n ? suffixes[indices[nextIndex]].rank0 : -1;
            }

            Arrays.sort(suffixes);
        }

        for(int i = 0; i < n; i++)
            array[i] = suffixes[i].index;
    }

    /**
     * Gets the index of the i-th suffix in the array.
     * @param i the original index
     * @return the transformed index
     */
    public int getSuffix(int i) {
        return array[i];
    }

    private final class Suffix implements Comparable<Suffix> {
        private int index, rank0, rank1;

        @Override
        public int compareTo(Suffix that) {
            if(this.rank0 == that.rank0)
                return Integer.compare(this.rank1, that.rank1);
            else
                return Integer.compare(this.rank0, that.rank0);
        }
    }
}
