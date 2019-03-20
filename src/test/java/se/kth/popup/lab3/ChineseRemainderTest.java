package se.kth.popup.lab3;

import org.junit.jupiter.api.Test;
import se.kth.popup.lab3.arithmetic.ChineseRemainder;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ChineseRemainderTest {

    @Test
    void test() {
        final Random random = new Random(0);
        final int max = 1_000_000_000;
        int count = 0;

        for(int i = 0; i < 100000; i++) {
            final int n = random.nextInt(max) + 1, m = random.nextInt(max) + 1;
            final int a = random.nextInt(n), b = random.nextInt(m);

            if(gcd(n, m) == 1) {
                count++;

                final ChineseRemainder.Solution solution = ChineseRemainder.solve(a, n, b, m);

                assertEquals((long) n * m, solution.k);
                assertTrue(solution.x >= 0 && solution.x < solution.k);
                assertEquals(a, solution.x % n);
                assertEquals(b, solution.x % m);
            }
        }

        System.out.println(count);
    }

    private long gcd(long a, long b) {
        long tmp;
        while(b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
