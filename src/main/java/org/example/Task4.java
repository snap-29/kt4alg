package org.example;

public class Task4 {

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();

        int[] values = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            left[i] = scanner.nextInt();
            right[i] = scanner.nextInt();
        }

        int[] order = new int[n];
        int size = 1;
        order[0] = 0;

        for (int i = 0; i < size; i++) {
            int node = order[i];

            if (left[node] != -1) {
                order[size++] = left[node];
            }

            if (right[node] != -1) {
                order[size++] = right[node];
            }
        }

        long[] bestDown = new long[n];
        long answer = Long.MIN_VALUE;

        for (int i = size - 1; i >= 0; i--) {
            int node = order[i];

            long leftGain = left[node] == -1
                    ? 0
                    : Math.max(0L, bestDown[left[node]]);

            long rightGain = right[node] == -1
                    ? 0
                    : Math.max(0L, bestDown[right[node]]);

            long throughNode = values[node] + leftGain + rightGain;

            answer = Math.max(answer, throughNode);

            bestDown[node] =
                    values[node] + Math.max(leftGain, rightGain);
        }

        System.out.println(answer);
    }
}