package Prometheus_week8;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {
    public T Head;

    Map<T,Edge<T>> InConnections = new HashMap<>();
    Map<T,Edge<T>> OutConnections = new HashMap<>();

    public Vertex(T head, Edge<T> edge) {
        Head = head;
        addConnection(edge);
    }

    public void addConnection(Edge<T> edge) {
        if (edge.Begin == Head
                && !OutConnections.containsKey(edge.End)) {
            OutConnections.put(edge.End, edge);
        }
        else if (edge.End == Head
                && !InConnections.containsKey(edge.Begin)) {
            InConnections.put(edge.Begin,edge);
        }
    }
}