package Prometheus_week6;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import static Prometheus_week6.Helper.readFile;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week6\\%s",
                System.getProperty("user.dir"), "test_06.txt"));
        ArrayList<String> stringArrayList = readFile(inputFile.toPath());

        HashSet<Long> hash = new HashSet<>();
        HashSet<Integer> sums = new HashSet<>();

        for (String aStringArrayList : stringArrayList) {
            long parseElement = Long.parseLong(aStringArrayList);
            hash.add(parseElement);
        }

        for (int i = -1000; i <= 1000; i++) {
            for (long a : hash) {
                if (hash.contains(i - a) && !sums.contains(i))
                    sums.add(i);
            }
        }

        System.out.printf("Sums amount %d ", sums.size());
    }
}
