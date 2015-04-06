package Prometheus_week8;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static Prometheus_week8.Helper.readFile;

public class Main {

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        HashMap<Integer,HashMap<Integer,Integer>> graphDfs = new HashMap<>();

        Graph<Integer> tg = new Graph<>();
        HashMap<Integer,HashMap<Integer,Integer>> tGraphDfs = new HashMap<>();

        System.out.print("Reading input file: ");
        File inputFile = new File(String.format("%s\\src\\Prometheus_week8\\testData\\%s",
                System.getProperty("user.dir"), "test_08_1.txt"));
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
        for (Integer head : g.Vertexes.keySet()) {
            System.out.print(".");
            graphDfs.put(head, Graph.depthFirstSearch(g, head));
        }
        System.out.println(" -- DONE");

        System.out.print("Computing DFSLoop(Gt): ");
        for (Integer head : tg.Vertexes.keySet()) {
            System.out.print(".");
            tGraphDfs.put(head, Graph.depthFirstSearch(g, head));
        }
        System.out.println(" -- DONE");
    }
}