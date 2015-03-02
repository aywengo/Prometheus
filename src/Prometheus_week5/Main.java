package Prometheus_week5;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week5\\inputs\\%s",
                System.getProperty("user.dir"), "input_16_10000.txt"));
        ArrayList<String> stringArrayList = Prometheus_week4.Helper.readFile(inputFile.toPath());

        int size = Integer.parseInt(stringArrayList.get(0));
        HeapTwister<Integer> pyramid = new HeapTwister<>(size);

        for (int i = 1; i <= size; i++) {
            int parseInt = Integer.parseInt(stringArrayList.get(i));
            pyramid.insert(parseInt);

            if (i == 2015 || i == 9876)
                pyramid.dump(String.format("Iteration %s", i));
        }

    }
}
