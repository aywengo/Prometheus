package Prometheus_week5;

public interface IHeap<T extends Comparable<T>> {
    int parent(int i);

    int heapSize();

    void heapify();

    void dump(String caption);

    void push(T a);

    T pop();

    T getElement(int i);

    T[] heap();
}

