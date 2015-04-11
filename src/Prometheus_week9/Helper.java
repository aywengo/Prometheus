package Prometheus_week9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Helper {
    public static Graph<Integer> getGraphFromFile(String fileName) throws IOException {
        File file = new File(String.format("%s\\src\\Prometheus_week9\\test\\%s",
                System.getProperty("user.dir"), fileName));

        Graph<Integer> result = new Graph<>();

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String[] sizes = sc.nextLine().split("[ ]+");
            int expectedVAmount = Integer.parseInt(sizes[0]);

            while (sc.hasNextLine()) {
                String[] items = sc.nextLine().split("[ ]+");

                result.addEdge(
                        Integer.parseInt(items[0]),
                        Integer.parseInt(items[1]),
                        Integer.parseInt(items[2]));
            }

            if (result.Vertexes.size() != expectedVAmount) {
                System.out.printf("Expected amount %d but was parsed %d%n", expectedVAmount, result.Vertexes.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return result;
    }

    public static int[][] parseRelationMatrix(String fileName, int amount) throws IOException {
        File file = new File(String.format("%s\\src\\Prometheus_week9\\test\\%s",
                System.getProperty("user.dir"), fileName));

        int[][] result = new int[amount][amount];

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            int line = 0;
            while (sc.hasNextLine()) {
                String[] items = sc.nextLine().split("[ ]+");
                for (int i = 0; i < items.length; i++) {
                    result[line][i] = tryParse(items[i]);
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return result;
    }

    public static int[][] getRelationMatrix(Graph<Integer> graph) {
        graph.compilePaths();

        int size = Collections.max(graph.Vertexes.keySet());
        int[][] result = new int[size][size];

        for (int i = 1; i <= size; i++) {
            if (graph.Distances.containsKey(i)) {
                Map<Integer, Integer> distanceSubMap = graph.Distances.get(i);
                for (int j = 1; j <= size; j++) {
                    Integer val = distanceSubMap.get(j);
                    if (val == null) {
                        val = Graph.INFINITY;
                    }
                    result[i - 1][j - 1] = val;
                }
            } else {
                for (int j = 1; j <= size; j++) {
                    result[i - 1][j - 1] = j == i ? 0 : Graph.INFINITY;
                }
            }
        }

        return result;
    }

    private static Integer tryParse(String text) {
        try {
            return new Integer(text);
        } catch (Exception e) {
            return Graph.INFINITY;
        }
    }
}
