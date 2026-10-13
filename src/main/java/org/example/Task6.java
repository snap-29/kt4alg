package org.example;

import java.util.TreeMap;

public class Task6 {

    private static class MedianWindow {
        private final TreeMap<Integer, Integer> lower = new TreeMap<>();
        private final TreeMap<Integer, Integer> upper = new TreeMap<>();

        private int lowerSize;
        private int upperSize;

        private static void addValue(
                TreeMap<Integer, Integer> map,
                int value
        ) {
            map.merge(value, 1, Integer::sum);
        }

        private static void removeValue(
                TreeMap<Integer, Integer> map,
                int value
        ) {
            int count = map.get(value);

            if (count == 1) {
                map.remove(value);
            } else {
                map.put(value, count - 1);
            }
        }

        public void add(int value) {
            if (lower.isEmpty() || value <= lower.lastKey()) {
                addValue(lower, value);
                lowerSize++;
            } else {
                addValue(upper, value);
                upperSize++;
            }

            balance();
        }

        public void remove(int value) {
            if (lower.containsKey(value)) {
                removeValue(lower, value);
                lowerSize--;
            } else {
                removeValue(upper, value);
                upperSize--;
            }

            balance();
        }

        private void balance() {
            while (lowerSize > upperSize + 1) {
                int value = lower.lastKey();

                removeValue(lower, value);
                lowerSize--;

                addValue(upper, value);
                upperSize++;
            }

            while (lowerSize < upperSize) {
                int value = upper.firstKey();

                removeValue(upper, value);
                upperSize--;

                addValue(lower, value);
                lowerSize++;
            }
        }

        public long twiceMedian() {
            if (lowerSize == upperSize) {
                return (long) lower.lastKey() + upper.firstKey();
            }

            return 2L * lower.lastKey();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] window = new int[k];

        MedianWindow medianWindow = new MedianWindow();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            int slot = i % k;

            if (i >= k) {
                medianWindow.remove(window[slot]);
            }

            window[slot] = value;
            medianWindow.add(value);

            if (i >= k - 1) {
                if (output.length() > 0) {
                    output.append(' ');
                }

                MedianFormatter.append(
                        output,
                        medianWindow.twiceMedian()
                );
            }
        }

        System.out.println(output);
    }
}