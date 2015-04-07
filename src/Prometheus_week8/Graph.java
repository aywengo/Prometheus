package Prometheus_week8;

import javax.management.OperationsException;
import java.util.*;

public class Graph<T extends Comparable<T>> {
    HashSet<Edge<T>> Edges = new HashSet<>();
    HashMap<T, Vertex<T>> Vertexes = new HashMap<>();
    HashMap<T, Integer> Times = new HashMap<>();
    ArrayList<Integer> componentCapacities = new ArrayList<>();
    int k = 0;

    public void strongConnectedComponentComputing() {
        while (k < Vertexes.size()) {
            T v = getNextUndiscovered();
            if (v == null) break;

            depthFirstSearch(v);
            System.out.print(".");
        }
    }

    public void strongConnectedComponentComputing(HashMap<T, Integer> map)
            throws OperationsException {
        map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(ord -> {
                    if (!Times.containsKey(ord.getKey())) {
                        depthFirstSearch(ord.getKey());
                        componentCapacities.add(Times.size() - componentCapacities.stream().mapToInt(Integer::intValue).sum());
                    }
                    System.out.print(".");
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
        try {
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
        } catch (Exception ex) {
            System.out.print(ex.toString());
            return false;
        }

        return true;
    }

    private void depthFirstSearch(T s) {
        Stack<T> stack = new Stack<>();
        stack.push(s);

        while (!stack.empty()) {
            T v = stack.pop();
            Vertex<T> vertex = Vertexes.get(v);

            if (!Times.containsKey(v)) {
                Times.put(v, ++k);
                for (T head : vertex.OutConnections.keySet()) {
                    if (Times.containsKey(head))
                        continue;

                    stack.push(head);
                }
            }
        }
    }
}