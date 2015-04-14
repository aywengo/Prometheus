package Prometheus_week9;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int start = 100562;
        int destination = 1070345;
        String map = "USA-FLA";

        analysePath(start, destination, map);
    }

    private static void analysePath(int start, int destination, String map) {
        try {
            System.out.printf("[%s] - Loading %s map ...", new Date(),map);
            Graph<Integer> graph = Helper.getGraphFromFile(map + ".txt");
            System.out.printf(" -- DONE [%s]%n", new Date());
            System.out.printf("[%s] - Calculation distances from %d to %d%n",
                    new Date(),start, destination);
            Map<Integer, Integer> result = graph.compileShortestPaths(start);

            System.out.printf("[%s] - The shortest distance from %d to %d is %d%n",
                    new Date(), start, destination, result.get(destination));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
