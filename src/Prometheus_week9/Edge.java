package Prometheus_week9;

public class Edge<T extends Comparable<T>> {
    public T Begin;
    public T End;
    public int Weight;

    public Edge(T begin, T end, int weight) {
        Begin = begin;
        End = end;
        Weight = weight;
    }
}
