package Prometheus_week5;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week5\\%s",
                System.getProperty("user.dir"), "input_03_10.txt"));
        ArrayList<String> stringArrayList = Prometheus_week4.Helper.readFile(inputFile.toPath());

        int size = Integer.parseInt(stringArrayList.get(0));

        MinHeap<Integer> minH = new MinHeap<>(size);
        MaxHeap<Integer> maxH = new MaxHeap<>(size);

        for (int i = 1; i <= size; i++) {
            int parseInt = Integer.parseInt(stringArrayList.get(i));
            minH.push(parseInt);
            maxH.push(parseInt);
        }

        minH.dump("MinHeap:");
        maxH.dump("MaxHeap:");
    }
}
