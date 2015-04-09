package Prometheus_week8;

import javax.management.OperationsException;
import java.util.*;

public class Graph<T extends Comparable<T>> {
    Set<Edge<T>> Edges = new HashSet<>();
    Map<T, Vertex<T>> Vertexes = new HashMap<>();
    Map<T, Integer> Times = new HashMap<>();
    Map<T, Integer> reversedTimes = new HashMap<>();
    List<Integer> componentCapacities = new ArrayList<>();
    Set<T> discovered = new HashSet<>();
    Set<T> reversedDiscovered = new HashSet<>();
    int k = 0, rk =0;

    public void strongConnectedComponentComputing() {
        k = 0;
        while (k < Vertexes.size()) {
            Optional<T> v = getNextUndiscovered();
            if (v.isPresent())
                depthFirstSearch(v.get());
        }
    }

    public void strongConnectedComponentComputing(Map<T, Integer> map)
            throws OperationsException {
        k = 0;
        map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(ord -> {
                    if (!discovered.contains(ord.getKey())) {
                        depthFirstSearch(ord.getKey());
                        componentCapacities.add(discovered.size() - componentCapacities.stream().mapToInt(Integer::intValue).sum());
                    }
                });
    }

    public void sccAmountsComputing()
            throws OperationsException {
        rk = 0;
        Times.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(ord -> {
                    if (!reversedDiscovered.contains(ord.getKey())) {
                        depthFirstSearchReversed(ord.getKey());
                        componentCapacities.add(reversedDiscovered.size() - componentCapacities.stream().mapToInt(Integer::intValue).sum());
                    }
                });
    }

    private Optional<T> getNextUndiscovered() {
        return Vertexes.keySet().stream().filter(ks -> !discovered.contains(ks)).findAny();
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
        stack.addLast(s);
        discovered.add(s);

        while (!stack.isEmpty()) {
            T v = stack.getFirst();
            Vertex<T> vertex = Vertexes.get(v);

            if (vertex.OutConnections.isEmpty()
                    ||  vertex.OutConnections.keySet().stream().allMatch(discovered::contains)){
                Times.put(v, ++k);
                stack.remove(v);
            }
            else {
                vertex.OutConnections.keySet().stream()
                        .filter(vo -> !discovered.contains(vo))
                        .forEach(e -> {
                            discovered.add(e);
                            stack.addFirst(e);
                        });
            }
        }
    }

    private void depthFirstSearchReversed(T s) {
        Deque<T> stack = new ArrayDeque<>();
        stack.addLast(s);
        reversedDiscovered.add(s);

        while (!stack.isEmpty()) {
            T v = stack.getFirst();
            Vertex<T> vertex = Vertexes.get(v);

            if (vertex.InConnections.isEmpty()
                    ||  vertex.InConnections.keySet().stream().allMatch(reversedDiscovered::contains)){
                reversedTimes.put(v, ++k);
                stack.remove(v);
            }
            else {
                vertex.InConnections.keySet().stream()
                        .filter(vo -> !reversedDiscovered.contains(vo))
                        .forEach(e -> {
                            reversedDiscovered.add(e);
                            stack.addFirst(e);
                        });
            }
        }
    }
}