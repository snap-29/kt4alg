package org.example;

public class MedianFormatter {

    public static void append(StringBuilder output, long twiceMedian) {
        if (twiceMedian < 0) {
            output.append('-');
        }

        long absolute = Math.abs(twiceMedian);

        output.append(absolute / 2);
        output.append(absolute % 2 == 0 ? ".0" : ".5");
    }
}