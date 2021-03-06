package Prometheus_week9;

import java.util.Map;
import java.util.TreeMap;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>{
    public T Head;

    Map<T, Edge<T>> InConnections = new TreeMap<>();
    Map<T, Edge<T>> OutConnections = new TreeMap<>();

    public Vertex(T head, Edge<T> edge) {
        Head = head;
        addConnection(edge);
    }

    public void addConnection(Edge<T> edge) {
        if (edge.Begin.equals(edge.End))
            return;

        if (Head.equals(edge.Begin)) {
            OutConnections.putIfAbsent(edge.End, edge);
        }

        if (Head.equals(edge.End)) {
            InConnections.put(edge.Begin, edge);
        }
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return o.Head.compareTo(Head);
    }
}