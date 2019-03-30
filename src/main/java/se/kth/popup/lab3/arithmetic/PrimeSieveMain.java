package se.kth.popup.lab3.arithmetic;

import java.util.Scanner;

public class PrimeSieveMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt(), q = scanner.nextInt();

        final PrimeSieve helper = new PrimeSieve(n);

        System.out.println(helper.count());

        for(int i = 0; i < q; i++) {
            final int x = scanner.nextInt();

            System.out.println(helper.isPrime(x) ? 1 : 0);
        }

        scanner.close();
    }
}
