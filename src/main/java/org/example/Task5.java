package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Task5 {

    private static class Pair {
        final int i;
        final int j;
        final long sum;

        Pair(int i, int j, long sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }

    public static long kthSmallestSum(int[] a, int[] b, int k) {
        if (a.length > b.length) {
            int[] temporary = a;
            a = b;
            b = temporary;
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>(
                Comparator.comparingLong((Pair pair) -> pair.sum)
        );

        int rows = Math.min(a.length, k);

        for (int i = 0; i < rows; i++) {
            queue.offer(new Pair(i, 0, (long) a[i] + b[0]));
        }

        long answer = 0;

        for (int position = 1; position <= k; position++) {
            Pair current = queue.remove();
            answer = current.sum;

            if (position < k && current.j + 1 < b.length) {
                int nextJ = current.j + 1;

                queue.offer(new Pair(
                        current.i,
                        nextJ,
                        (long) a[current.i] + b[nextJ]
                ));
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(kthSmallestSum(a, b, k));
    }
}