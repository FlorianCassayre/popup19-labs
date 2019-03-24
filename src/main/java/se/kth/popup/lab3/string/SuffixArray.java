package se.kth.popup.lab3.string;

import java.util.Arrays;

public final class SuffixArray {
    private final int[] sa;

    /**
     * Constructs the suffix array for that string.
     * @param string the string
     */
    public SuffixArray(String string) {
        final int n = string.length();

        this.sa = new int[n];

        final int[][] p = new int[log(n) + 1][n];
        final int[] first = new int[n], second = new int[n], index = new int[n];
        final Integer[] order = new Integer[n];

        for(int i = 0; i < n; i++) {
            p[0][i] = string.charAt(i);
            order[i] = i;
        }

        int step = 1;

        for(int i = 0; 1 << i < n; i++, step++) {
            for(int j = 0; j < n; j++) {
                index[order[j]] = j;
                first[order[j]] = p[i][j];
                second[order[j]] = (j + (1 << i) < n ? p[i][j + (1 << i)] : -1);
            }

            Arrays.sort(order, (a, b) -> {
                if(first[a] != first[b])
                    return Integer.compare(first[a], first[b]);
                else if(second[a] != second[b])
                    return Integer.compare(second[a], second[b]);
                else
                    return Integer.compare(index[a], index[b]);
            });

            for(int j = 0; j < n; j++) {
                p[i + 1][index[order[j]]] =
                        ((j > 0 && first[order[j]] == first[order[j - 1]] && second[order[j]] == second[order[j - 1]])
                        ? p[i + 1][index[order[j - 1]]]
                        : j);
            }
        }

        step--;

        for(int i = 0; i < n && n > 1; i++) {
            sa[p[step][i]] = i;
        }
    }

    private static int log(int n) {
        int i = 0;
        while(1 << i < n)
            i++;
        return i;
    }

    /**
     * Gets the index of the i-th suffix in the array.
     * @param i the original index
     * @return the transformed index
     */
    public int getSuffix(int i) {
        return sa[i];
    }
}
