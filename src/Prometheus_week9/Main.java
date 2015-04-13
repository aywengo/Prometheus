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
            System.out.printf("[%s] - Calculation distances from %d to %d%n", new Date(),start, destination);
            Map<Object[], Integer> result = graph.compileAllPossiblePaths(start, destination);
            int shortest = Collections.min(result.values());
            long amountOfShortest = result.values().stream().filter(a -> a == shortest).count();
            System.out.printf("[%s] - The shortest distance from %d to %d is %d%n", new Date(), start, destination, shortest);
            System.out.printf("[%s] - Amount found of shortest paths from %d to %d is %d%n", new Date(), start, destination, amountOfShortest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
