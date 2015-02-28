package Prometheus_week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Helper {

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

    public static <T> void swap(T[] A, int x, int y) {
        T z = A[x];
        A[x] = A[y];
        A[y] = z;
    }
}
