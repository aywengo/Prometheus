package Prometheus_week8;

import javax.management.OperationsException;
import java.util.*;
import java.util.stream.*;

public class Graph<T extends Comparable<T>> {
    Set<Edge<T>> Edges = new HashSet<>();
    Map<T, Vertex<T>> Vertexes = new HashMap<>();
    Map<T, Integer> Times = new HashMap<>();
    Map<T, Integer> reversedTimes = new HashMap<>();
    List<Integer> componentCapacities = new ArrayList<>();
    Set<T> discovered = new HashSet<>();
    Set<T> reversedDiscovered = new HashSet<>();
    int k = 0, rk = 0;

    public void DFSLoop() {
        k = 0;
        while (k < Vertexes.size()) {
            Optional<T> v = getNextUndiscovered();
            if (v.isPresent())
                depthFirstSearch(v.get());
        }
    }

    public void DFSLoopGt()
            throws OperationsException {
        rk = 0;
        Times.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(ord -> {
                    if (!reversedDiscovered.contains(ord.getKey())) {
                        depthFirstSearchReversed(ord.getKey());
                        componentCapacities
                                .add(reversedDiscovered.size()
                                        - componentCapacities.stream().mapToInt(Integer::intValue).sum());
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
        stack.push(s);
        discovered.add(s);

        while (!stack.isEmpty()) {
            T v = stack.getFirst();
            Vertex<T> vertex = Vertexes.get(v);

            ArrayList<T> listOfConnectedUndiscovered = vertex.OutConnections.keySet().stream()
                    .filter(vo -> !discovered.contains(vo))
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!listOfConnectedUndiscovered.isEmpty()) {
                discovered.addAll(listOfConnectedUndiscovered);
                listOfConnectedUndiscovered.forEach(stack::addFirst);
            }
            else {
                Times.put(stack.pop(), ++k);
            }
        }
    }

    private void depthFirstSearchReversed(T s) {
        Deque<T> stack = new ArrayDeque<>();
        stack.push(s);
        reversedDiscovered.add(s);

        while (!stack.isEmpty()) {
            T v = stack.getFirst();
            Vertex<T> vertex = Vertexes.get(v);

            ArrayList<T> listOfConnectedUndiscovered = vertex.InConnections.keySet().stream()
                    .filter(vo -> !reversedDiscovered.contains(vo))
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!listOfConnectedUndiscovered.isEmpty()) {
                reversedDiscovered.addAll(listOfConnectedUndiscovered);
                listOfConnectedUndiscovered.forEach(stack::addFirst);
            }
            else {
                reversedTimes.put(stack.pop(), ++rk);
            }
        }
    }
}