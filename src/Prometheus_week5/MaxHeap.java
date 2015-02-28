package Prometheus_week5;

import java.lang.Comparable;

public class MaxHeap<T extends Comparable<T>> extends BinaryHeapBase<T> {

    private T[] heap;

    public MaxHeap(T[] A) {
        heap = A;
    }

    public void maxHeapify(IBinaryHeap<T> A, int i) {
        int l = A.left(i);
        int r = A.right(i);

        int largest = i;
        if (l <= A.heapSize() && A.getElement(l) > A.getElement(i)) {
            largest = l;
        }

        if (r <= A.heapSize() && A.getElement(r) > A.getElement(largest)) {
            largest = r;
        }

        if (largest != i) {
            Helper.swap(A.heap(), i, largest);
            maxHeapify(A, largest);
        }
    }

    public void buildMaxHeap(IBinaryHeap<T> A) {
        for (int i = A.heapSize() + 1; i > 0; i--) {
            maxHeapify(A, i - 1);
        }
    }

    @Override
    public int heapSize() {
        return heap.length;
    }

    @Override
    public int getElement(int i) {
        return heap[i];
    }

    @Override
    public int[] heap() {
        return heap;
    }
}
