package Prometheus_week8;

import javax.management.OperationsException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static Prometheus_week8.Helper.readFile;

public class Main {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        Graph<Integer> tg = new Graph<>();
        HashMap<Integer, Integer> sums = new HashMap<>();

        System.out.print("Reading input file: ");
        try {
            File inputFile = new File(String.format("%s\\src\\Prometheus_week8\\testData\\%s",
                    System.getProperty("user.dir"), "test_08_2.txt"));
            ArrayList<String> stringArrayList = readFile(inputFile.toPath());


            for (String aStringArrayList : stringArrayList) {
                System.out.print(".");
                String[] edges = aStringArrayList.split("[ ]+");
                int begin = Integer.parseInt(edges[0]);
                int end = Integer.parseInt(edges[1]);
                g.addEdge(begin, end);
                tg.addEdge(end, begin);
            }
            System.out.println(" -- DONE");

            System.out.print("Computing DFSLoop(G): ");
            g.strongConnectedComponentComputing();
            System.out.println(" -- DONE");

            System.out.print("Computing DFSLoop(Gt): ");

            tg.strongConnectedComponentComputing(g.Times);

            System.out.println(" -- DONE");


            System.out.print("Graph order complexity: ");
            g.Times.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .forEachOrdered(ord -> {
                        System.out.print(ord + " ");
                    });
            System.out.println();

            System.out.print("TGraf order complexity: ");
            tg.Times.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .forEachOrdered(ord -> {
                        System.out.print(ord + " ");
                    });
            System.out.println();

            System.out.print("Capacities: ");
            tg.componentCapacities.stream()
                    .sorted(Collections.reverseOrder())
                    .forEach(o -> System.out.print(o + " "));
            System.out.println();

        } catch (OperationsException e) {
            e.printStackTrace();
        }
    }
}