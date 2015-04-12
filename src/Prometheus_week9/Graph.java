package Prometheus_week9;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Graph<T extends Comparable<T>> {
    public static int INFINITY = Integer.MAX_VALUE;
    Set<Edge<T>> Edges = new HashSet<>();
    Map<T, Vertex<T>> Vertexes = new HashMap<>();
    Map<T, Map<T, Integer>> Distances = new HashMap<>();

    public void addEdge(T begin, T end, int weight) throws OperationNotSupportedException {
        if (weight < 0) {
            throw new OperationNotSupportedException();
        }

        Edge<T> toAdd = new Edge<>(begin, end, weight);

        if (!Edges.contains(toAdd)) {
            Edges.add(toAdd);
            insertUpdateVertex(toAdd);
        }
    }

    private void insertUpdateVertex(Edge<T> edge) {
        if (Vertexes.containsKey(edge.Begin)) {
            Vertexes.get(edge.Begin).addConnection(edge);
        } else {
            Vertexes.put(edge.Begin, new Vertex<>(edge.Begin, edge));
        }

        if (Vertexes.containsKey(edge.End)) {
            Vertexes.get(edge.End).addConnection(edge);
        } else {
            Vertexes.put(edge.End, new Vertex<>(edge.End, edge));
        }
    }

    public Map<Object[], Integer> compileAllPossiblePaths(T start, T destination) {
        TreeMap<Vertex<T>, Integer> Q = new TreeMap<>();
        Map<T, Integer> A = new HashMap<>();
        Map<T, T> B = new HashMap<>();
        Map<Object[], Integer> result = new HashMap<>();

        for (Vertex<T> v : Vertexes.values()) {
            if (v.Head.equals(start)) {
                A.putIfAbsent(v.Head, 0);
            } else {
                A.putIfAbsent(v.Head, INFINITY);
            }
            Q.put(v, A.get(v.Head));
        }

        while (!Q.isEmpty()) {
            Vertex<T> v =  Q.pollLastEntry().getKey();

            v.OutConnections.values().stream().forEach(u -> {
                int weight = A.get(v.Head) + u.Weight;

                if (A.get(u.End) > weight && weight >= 0) {
                    B.put(u.End, v.Head);
                    A.put(u.End, weight);
                    Q.put(Vertexes.get(u.End), weight);

                    if (u.End.equals(destination)) {
                        Deque<T> path = new ArrayDeque<>();
                        path.addFirst(u.End);
                        while (!path.peek().equals(start)) {
                            path.addFirst(B.get(path.peek()));
                        }
                        result.put(path.toArray(), weight);
                        System.out.printf("Got to endpoint with weight %d%n", weight);
                    }
                }
            });
        }
        return result;
    }

    public Map<T, Integer> compileShortestPaths(T start) {
        TreeMap<Vertex<T>, Integer> Q = new TreeMap<>();
        Map<T, Integer> A = new HashMap<>();
        for (Vertex<T> v : Vertexes.values()) {
            if (v.Head.equals(start)) {
                A.putIfAbsent(v.Head, 0);
            } else {
                A.putIfAbsent(v.Head, INFINITY);
            }
            Q.put(v, A.get(v.Head));
        }

        while (!Q.isEmpty()) {
            Vertex<T> v =
                    Q.pollLastEntry().getKey();

            v.OutConnections.values().stream().forEach(u -> {
                int weight = A.get(v.Head) + u.Weight;

                if (A.get(u.End) > weight && weight >= 0) {
                    A.put(u.End, weight);
                    Q.put(Vertexes.get(u.End), weight);
                }
            });
        }
        return A;
    }

    public void compilePaths() {
        Vertexes.keySet().forEach(v -> Distances.put(v, compileShortestPaths(v)));
    }
}