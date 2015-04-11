package Prometheus_week9;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T extends Comparable<T>> {
    public T Head;

    Map<T, Edge<T>> InConnections = new HashMap<>();
    Map<T, Edge<T>> OutConnections = new HashMap<>();

    public Vertex(T head, Edge<T> edge) {
        Head = head;
        addConnection(edge);
    }

    // TODO : refactor is required before final commit
    public void addConnection(Edge<T> edge) {
        if (edge.Begin.equals(edge.End))
            return;

        if (Head.equals(edge.Begin)) {
            if (OutConnections.containsKey(edge.End)) {
                if (OutConnections.get(edge.End).Weight > edge.Weight) {
                    OutConnections.put(edge.End, edge);
                }
            } else {
                OutConnections.put(edge.End, edge);
            }
        }

        if (Head.equals(edge.End)) {
//            InConnections.putIfAbsent(edge.Begin, edge);
            if (InConnections.containsKey(edge.Begin)) {
                if (InConnections.get(edge.Begin).Weight > edge.Weight) {
                    InConnections.put(edge.Begin, edge);
                }
            } else {
                InConnections.put(edge.Begin, edge);
            }
        }
    }
}