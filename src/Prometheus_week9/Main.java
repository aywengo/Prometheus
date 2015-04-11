package Prometheus_week9;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int start = 100562;
        int destination = 1070345;
        String map = "USA-FLA";

        analysePath(start, destination, map);
    }

    private static void analysePath(int start, int destination, String map) {
        try {
            System.out.printf("Loading %s map ...", map);
            Graph<Integer> graph = Helper.getGraphFromFile(map + ".txt");
            System.out.println(" -- DONE");
            System.out.printf("Calculation distances from %d to %d%n", start, destination);
            Map<Object[], Integer> result = graph.compileAllPossiblePaths(start, destination);
            int shortest = Collections.min(result.values());
            long amountOfShortest = result.values().stream().filter(a -> a == shortest).count();
            System.out.printf("The shortest distance from %d to %d is %d%n", start, destination, shortest);
            System.out.printf("Amount found of shortest paths from %d to %d is %d%n", start, destination, amountOfShortest);
            System.out.println(" -- DONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
