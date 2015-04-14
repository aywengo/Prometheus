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

    public Map<T, Integer> compileShortestPaths(T start) {
        ArrayDeque<Vertex<T>> Q = new ArrayDeque<>();
        Map<T, Integer> A = new HashMap<>();
        Vertexes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(vert -> Q.push(vert.getValue()));
        A.put(start, 0);

        while (!Q.isEmpty()) {
            Vertex<T> v = Q.pollLast();

            v.OutConnections.values().stream().forEach(u -> {
                if (A.containsKey(v.Head)) {
                    int weight = A.get(v.Head) + u.Weight;

                    if (!A.containsKey(u.End) || A.get(u.End) > weight) {
                        A.put(u.End, weight);
                        Q.push(Vertexes.get(u.End));
                    }
                }
            });
        }
        return A;
    }

    public void compilePaths() {
        Vertexes.keySet().forEach(v -> Distances.put(v, compileShortestPaths(v)));
    }
}