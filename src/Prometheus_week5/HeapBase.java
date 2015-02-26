package Prometheus_week5;

public abstract class HeapBase {
    protected int heapDegree;

    public int parent(int i) {
        return i / heapDegree;
    }

    public abstract int heapSize();

    public abstract int getElement(int i);
}
