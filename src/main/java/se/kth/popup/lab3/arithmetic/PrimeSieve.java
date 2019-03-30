package se.kth.popup.lab3.arithmetic;

import java.util.BitSet;

public class PrimeSieve {
    private final BitSet set;
    private final int count;

    public PrimeSieve(int n) {
        this.set = new BitSet(n);

        final int upper = (int) Math.floor(Math.sqrt(n + 1));
        int acc = n - 1;

        set.set(1);

        for(int i = 2; i <= upper; i++)
            if(!set.get(i))
                for(int j = i * i; j <= n; j += i) {
                    if(!set.get(j))
                        acc--;
                    set.set(j);
                }

        this.count = acc;
    }

    public boolean isPrime(int k) {
        return !set.get(k);
    }

    public int count() {
        return count;
    }
}
