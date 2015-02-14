package Prometheus_week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Helper {
    public static String countComparisons(File file, PivotElement pivot, String msg) {
        int comparisonsAmount = 0;
        ArrayList<String> input = readFile(file.toPath());

        int amount = Integer.parseInt(input.get(0));

        int[] A = new int[amount + 1];
        for (int i = 1; i < input.size(); i++) {
            int parseInt = Integer.parseInt(input.get(i));
            A[i] = parseInt;
        }

        int[] counter = new int[]{0};

        switch (pivot) {
            case FIRST:
                comparisonsAmount = QuickSort.sortByFirstElementAsPivot(A, 1, amount, counter);
                break;
            case LAST:
                comparisonsAmount = QuickSort.sortByLastElementAsPivot(A, 1, amount, counter);
                break;
            case MEDIANA:
                comparisonsAmount = QuickSort.sortByMedianElementAsPivot(A, 1, amount, counter);
                break;
        }

        return String.format(msg, comparisonsAmount);
    }

    public static ArrayList<String> readFile(java.nio.file.Path file) {
        ArrayList<String> result = new ArrayList<>();
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return result;
    }
}
