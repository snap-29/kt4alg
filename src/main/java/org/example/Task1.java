package org.example;

public class Task1 {

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

        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        queue[tail++] = 0;

        StringBuilder output = new StringBuilder();

        while (head < tail) {
            int levelSize = tail - head;
            long levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                int node = queue[head++];

                levelSum += values[node];

                if (left[node] != -1) {
                    queue[tail++] = left[node];
                }

                if (right[node] != -1) {
                    queue[tail++] = right[node];
                }
            }

            if (output.length() > 0) {
                output.append(' ');
            }

            output.append(levelSum);
        }

        System.out.println(output);
    }
}