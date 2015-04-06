package Prometheus_week8;

import java.io.File;
import java.util.ArrayList;

import static Prometheus_week8.Helper.readFile;

public class Main {

    public static void main(String[] args) {

        File inputFile = new File(String.format("%s\\src\\Prometheus_week8\\testData\\%s",
                System.getProperty("user.dir"), "test_08_1.txt"));
        ArrayList<String> stringArrayList = readFile(inputFile.toPath());

        Graph<Integer> g = new Graph<>();

        for (String aStringArrayList : stringArrayList) {
            String[] edges = aStringArrayList.split("[ ]+");
            int begin = Integer.parseInt(edges[0]);
            int end = Integer.parseInt(edges[1]);
            g.addEdge(begin, end);
        }
    }
}