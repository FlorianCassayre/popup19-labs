package se.kth.popup.lab4.geometry.line;

import se.kth.popup.lab4.geometry.Vector;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class LineSegmentIntersectionMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final NumberFormat formatter = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));

        final int n = scanner.nextInt();

        for(int i = 0; i < n; i++) {
            final LineSegment a = readLine(scanner), b = readLine(scanner);
            final LineSegment.Result result = a.intersection(b);

            if(result instanceof LineSegment.NoIntersection) {
                System.out.println("none");
            } else if(result instanceof LineSegment.SingleIntersection) {
                final LineSegment.SingleIntersection single = (LineSegment.SingleIntersection) result;
                System.out.println(formatter.format(single.x) + " " + formatter.format(single.y));
            } else if(result instanceof LineSegment.InfiniteIntersection) {
                final LineSegment.InfiniteIntersection multiple = (LineSegment.InfiniteIntersection) result;
                System.out.println(formatter.format(multiple.x1) + " " + formatter.format(multiple.y1) + " " + formatter.format(multiple.x2) + " " + formatter.format(multiple.y2));
            }
        }

        scanner.close();
    }

    private static LineSegment readLine(Scanner scanner) {
        return new LineSegment(readPoint(scanner), readPoint(scanner));
    }

    private static Vector readPoint(Scanner scanner) {
        return new Vector(scanner.nextInt(), scanner.nextInt());
    }
}
