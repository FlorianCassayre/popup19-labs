package se.kth.popup.lab3.string;

import java.util.Scanner;

public class SuffixSortingMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            final String string = scanner.nextLine();
            final SuffixArray array = new SuffixArray(string);
            final String[] queries = scanner.nextLine().split(" ");
            for(int i = 1; i < queries.length; i++) {
                final int q = Integer.parseInt(queries[i]);
                System.out.print(array.getSuffix(q));
                if(i < queries.length - 1)
                    System.out.print(" ");
                else
                    System.out.println();
            }
        }

        scanner.close();
    }
}
