package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task2 {

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            frequency.merge(value, 1, Integer::sum);
        }

        List<Integer> numbers = new ArrayList<>(frequency.keySet());

        numbers.sort((first, second) -> {
            int byFrequency = Integer.compare(
                    frequency.get(second),
                    frequency.get(first)
            );

            if (byFrequency != 0) {
                return byFrequency;
            }

            return Integer.compare(first, second);
        });

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < k; i++) {
            if (i > 0) {
                output.append(' ');
            }

            output.append(numbers.get(i));
        }

        System.out.println(output);
    }
}