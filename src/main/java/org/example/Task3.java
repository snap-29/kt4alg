package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task3 {

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();

        PriorityQueue<Integer> lower =
                new PriorityQueue<>(Comparator.reverseOrder());

        PriorityQueue<Integer> upper = new PriorityQueue<>();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();

            if (lower.isEmpty() || value <= lower.peek()) {
                lower.offer(value);
            } else {
                upper.offer(value);
            }

            if (lower.size() > upper.size() + 1) {
                upper.offer(lower.poll());
            } else if (lower.size() < upper.size()) {
                lower.offer(upper.poll());
            }

            long twiceMedian;

            if (lower.size() == upper.size()) {
                twiceMedian = (long) lower.peek() + upper.peek();
            } else {
                twiceMedian = 2L * lower.peek();
            }

            if (i > 0) {
                output.append(' ');
            }

            MedianFormatter.append(output, twiceMedian);
        }

        System.out.println(output);
    }
}