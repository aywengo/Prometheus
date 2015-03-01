package Prometheus_week5;

public class MinHeap<T extends Comparable<T>> extends BinaryHeapBase<T> {

    public MinHeap(int capacity) {
        super(capacity);
    }

    public void minHeapify(IBinaryHeap<T> A, int i) {
        int l = A.left(i);
        int r = A.right(i);

        int smallest = i;
        if (l <= A.heapSize() && A.getElement(l).compareTo(A.getElement(i)) < 0) {
            smallest = l;
        }

        if (r <= A.heapSize() && A.getElement(r).compareTo(A.getElement(smallest)) < 0) {
            smallest = r;
        }

        if (smallest != i) {
            Helper.swap(A.heap(), i, smallest);
            minHeapify(A, smallest);
        }
    }

    public void buildMinHeap(IBinaryHeap<T> A) {
        for (int i = A.heapSize(); i > 0; i--) {
            minHeapify(A, i);
        }
    }

    @Override
    public void heapify() {
        buildMinHeap(this);
    }
}
