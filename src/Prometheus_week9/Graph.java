package Prometheus_week9;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Graph<T extends Comparable<T>> {
    Set<Edge<T>> Edges = new HashSet<>();
    Map<T, Vertex<T>> Vertexes = new HashMap<>();

    public static int INFINITY = Integer.MAX_VALUE;

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

    public int compileShortestPath(T start, T destination){
        if (start.equals(destination)) {
            return 0;
        }

        Map<Vertex<T>,Integer> Q = new HashMap<>();
        Map<T,Integer> A = new HashMap<>();
        Map<T,T> B = new HashMap<>();
        for (Vertex<T> v : Vertexes.values()) {
            if (v.Head.equals(start)) {
                A.putIfAbsent(v.Head,0);
            } else {
                A.putIfAbsent(v.Head, INFINITY);
            }
            Q.put(v,A.get(v.Head));
        }

        while (!Q.isEmpty()) {
            Vertex<T> v = Q.entrySet().stream()
                    .min(Map.Entry.comparingByValue(Integer::compareTo))
                    .get().getKey();
            Q.remove(v);

            for (Edge<T> u : v.OutConnections.values()) {
                int weight = A.get(v.Head) + u.Weight;
                if (A.get(u.End) > weight) {
                    A.put(u.End, weight);
                    B.put(u.End, v.Head);
                    Q.put(Vertexes.get(u.End), weight);
                }
            }
        }

        return A.get(destination);
    }

    public int[][] getRelationMatrix() {
        int size = Vertexes.size();
        int[][] result = new int[size][size];

        int vc = 0;
        for (T v : Vertexes.keySet()) {
            int uc = 0;
            for (T u : Vertexes.keySet()) {
                result[vc][uc] = compileShortestPath(v , u);
                uc++;
            }
            vc++;
        }

        return result;
    }
}