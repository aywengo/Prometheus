package Prometheus_week5;

public class MaxHeap extends BinaryHeap {

    private int[] heap;

    public MaxHeap(int[] A) {
        heap = A;
    }

    public static void maxHeapify(IBinaryHeap A, int i) {
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

    public static void buildMaxHeap(IBinaryHeap A) {
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
