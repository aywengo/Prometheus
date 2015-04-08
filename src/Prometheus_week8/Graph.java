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
            Optional<T> v = getNextUndiscovered();
            if (v.isPresent())
                depthFirstSearch(v.get());
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

    private Optional<T> getNextUndiscovered() {
        return Vertexes.keySet().stream().filter(ks -> !Times.containsKey(ks)).findAny();
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
        k=Times.keySet().stream().max();
        Deque<T> stack = new ArrayDeque<>();
        stack.push(s);
        Set<T> discovered = new HashSet<>();
        discivered.addAll(Times.keySet());
        discovered.add(s);

        while (!stack.isEmpty()) {
            T v = stack.getFirst();
            Vertex<T> vertex = Vertexes.get(v);
            discovered.add(v);

            if (vertex.OutConnections.isEmpty()
                  ||  vertex.OutConnections.keySet().stream().allMatch(vo -> discovered.contains(vo))){
                    Times.put(v,++k);

                stack.remove(v);
            }
            else {
                vertex.OutConnections.keySet().stream()
                        .filter(vo -> !discovered.contains(vo))
                        .forEach(vo -> {
                            stack.addFirst(vo);
                        });
            }
        }
    }
}