/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
package se.kth.popup.lab4.hash;

public class SubstringHash {
    private static final long M1 = 3037000493L, M2 = 3037000453L, B1 = 29, B2 = 31;
    private static final char OFFSET = 'a';

    private final int n;
    private final long[] prefix1, prefix2, powers1, powers2;

    public SubstringHash(String string) {
        n = string.length();

        prefix1 = new long[n + 1];
        prefix2 = new long[n + 1];
        powers1 = new long[n];
        powers2 = new long[n];

        long prev1 = 0, prev2 = 0;
        long bn1 = 1, bn2 = 1;
        for(int i = 0; i < n; i++) {
            final long c = string.charAt(i) - OFFSET + 1;
            prev1 = ((prev1 + (c * bn1) % M1) % M1);
            prev2 = ((prev2 + (c * bn2) % M2) % M2);
            prefix1[i + 1] = prev1;
            prefix2[i + 1] = prev2;
            powers1[i] = bn1;
            powers2[i] = bn2;
            bn1 = (bn1 * B1) % M1;
            bn2 = (bn2 * B2) % M2;
        }
    }

    public long getHash(int i, int j) {
        final long c1 = (prefix1[j] - prefix1[i] + M1) % M1, c2 = (prefix2[j] - prefix2[i] + M2) % M2;

        return (((c1 * powers1[n - 1 - i]) % M1) << 32) | ((c2 * powers2[n - 1 - i]) % M2);
    }

}
