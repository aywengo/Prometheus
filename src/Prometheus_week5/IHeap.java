package Prometheus_week5;

public interface IHeap<T extends Comparable<T>> {
    int parent(int i);

    int heapSize();

    T getElement(int i);

    T[] heap();
}

