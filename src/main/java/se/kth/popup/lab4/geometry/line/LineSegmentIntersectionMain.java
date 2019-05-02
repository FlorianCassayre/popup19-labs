package se.kth.popup.lab4.geometry.line;

import se.kth.popup.lab4.geometry.Vector;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class LineSegmentIntersectionMain {
    private static final NumberFormat FORMATTER = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt();

        for(int i = 0; i < n; i++) {
            final LineSegment a = readLine(scanner), b = readLine(scanner);
            final LineSegment.Result result = a.intersection(b);

            if(result instanceof LineSegment.NoIntersection) {
                System.out.println("none");
            } else if(result instanceof LineSegment.SingleIntersection) {
                final LineSegment.SingleIntersection single = (LineSegment.SingleIntersection) result;
                System.out.println(format(single.x) + " " + format(single.y));
            } else if(result instanceof LineSegment.InfiniteIntersection) {
                final LineSegment.InfiniteIntersection multiple = (LineSegment.InfiniteIntersection) result;
                System.out.println(format(multiple.x1) + " " + format(multiple.y1) + " " + format(multiple.x2) + " " + format(multiple.y2));
            }
        }

        scanner.close();
    }

    private static String format(double d) {
        return FORMATTER.format(d).replace("-0.00", "0.00");
    }

    private static LineSegment readLine(Scanner scanner) {
        return new LineSegment(readPoint(scanner), readPoint(scanner));
    }

    private static Vector readPoint(Scanner scanner) {
        return new Vector(scanner.nextInt(), scanner.nextInt());
    }
}
