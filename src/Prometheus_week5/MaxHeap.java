package Prometheus_week5;

public class MaxHeap<T extends Comparable<T>> extends BinaryHeapBase<T> {

    public MaxHeap(int capacity) {
        super(capacity);
    }

    public void maxHeapify(IBinaryHeap<T> A, int i) {
        int l = A.left(i);
        int r = A.right(i);

        int largest = i;
        if (l <= A.heapSize() && A.getElement(l).compareTo(A.getElement(i)) > 0) {
            largest = l;
        }

        if (r <= A.heapSize() && A.getElement(r).compareTo(A.getElement(largest)) > 0) {
            largest = r;
        }

        if (largest != i) {
            Helper.swap(A.heap(), i, largest);
            maxHeapify(A, largest);
        }
    }

    public void buildMaxHeap(IBinaryHeap<T> A) {
        for (int i = A.heapSize(); i > 0; i--) {
            maxHeapify(A, i);
        }
    }

    @Override
    public void heapify() {
        buildMaxHeap(this);
    }
}
