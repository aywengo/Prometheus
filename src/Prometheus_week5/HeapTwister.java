package Prometheus_week5;

public class HeapTwister<T extends Comparable<T>> {
    public MinHeap<T> High;
    public MaxHeap<T> Low;


    private boolean hasSizeEven() {
        return size() % 2 == 0;
    }

    private int size() {
        return High.heapSize() + Low.heapSize();
    }
}
