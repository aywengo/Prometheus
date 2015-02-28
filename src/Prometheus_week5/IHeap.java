package Prometheus_week5;

import java.lang.*;

public interface IHeap<T> {
    int parent(int i);

    int heapSize();

    T getElement(int i);

    T[] heap();
}

