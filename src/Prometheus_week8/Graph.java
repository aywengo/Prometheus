package Prometheus_week8;

import javax.management.OperationsException;
import java.util.*;

public class Graph<T extends Comparable<T>> {
    Set<Edge<T>> Edges = new HashSet<>();
    Map<T, Vertex<T>> Vertexes = new HashMap<>();
    Map<T, Integer> Times = new HashMap<>();
    List<Integer> componentCapacities = new ArrayList<>();
    int k = 0;

    public void strongConnectedComponentComputing() {
        while (k < Vertexes.size()) {
            T v = getNextUndiscovered();
            if (v == null) break;

            depthFirstSearch(v);
        }
    }

    public void strongConnectedComponentComputing(Map<T, Integer> map)
            throws OperationsException {
        map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(ord -> {
                    if (!Times.containsKey(ord.getKey())) {
                        depthFirstSearch(ord.getKey());
                        componentCapacities.add(Times.size() - componentCapacities.stream().mapToInt(Integer::intValue).sum());
                    }
                });
    }

    private T getNextUndiscovered() {
        for (T i : Vertexes.keySet()) {
            if (!Times.containsKey(i)) return i;
        }

        return null;
    }

    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 1);
    }

    public boolean addEdge(T begin, T end, int weight) {
        Edge<T> toAdd = new Edge<>(begin, end, weight);

        if (Edges.contains(toAdd)) {
            return false;
        }

        Edges.add(toAdd);
        return insertUpdateVertex(toAdd);
    }

    private boolean insertUpdateVertex(Edge<T> edge) {
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

        return true;
    }

    private void depthFirstSearch(T s) {
        Deque<T> stack = new ArrayDeque<>();
        stack.addFirst(s);
        Times.put(s, ++k);

        while (!stack.isEmpty()) {
            T v = stack.peek();
            Vertex<T> vertex = Vertexes.get(v);
            boolean toRemove = true;

            for (T head : vertex.OutConnections.keySet()) {
                if (Times.containsKey(head)) continue;
                toRemove = false;
                Times.put(head, ++k);
                stack.addFirst(head);
            }

            if (toRemove) {
                stack.remove(v);
            }
        }
    }
}