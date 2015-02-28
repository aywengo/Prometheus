package Prometheus_week5;

public interface IBinaryHeap<T extends Comparable<T>> extends IHeap<T> {
    int left(int i);

    int right(int i);
}
