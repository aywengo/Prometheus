package Prometheus_week8;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T extends Comparable<T>> {
    public T Head;

    Map<T,Edge<T>> InConnections = new HashMap<>();
    Map<T,Edge<T>> OutConnections = new HashMap<>();

    public Vertex(T head, Edge<T> edge) {
        Head = head;
        addConnection(edge);
    }

    public void addConnection(Edge<T> edge) {
        if (Head.equals(edge.Begin)) {
            OutConnections.putIfAbsent(edge.End, edge);
        }

        if (Head.equals(edge.End)) {
            InConnections.putIfAbsent(edge.Begin, edge);
        }
    }
}