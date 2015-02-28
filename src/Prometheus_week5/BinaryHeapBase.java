package Prometheus_week5;

public abstract class BinaryHeapBase<T extends Comparable<T>> implements IBinaryHeap<T> {
    protected int aring = 2;

    public int parent(int i) {
        return i / aring;
    }

    public abstract int heapSize();

    public abstract T getElement(int i);

    public int left(int i) {
        return aring * i;
    }

    public int right(int i) {
        return aring * i + 1;
    }
}
