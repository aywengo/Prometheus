package Prometheus_week5;

public abstract class BinaryHeapBase<T extends Comparable<T>> implements IBinaryHeap<T> {
    protected int arcing;
    protected T[] heap;
    protected int size;

    public BinaryHeapBase(int capacity) {
        arcing = 2;
        size = 0;
        heap = (T[]) new Comparable[capacity + 1];
    }

    public int parent(int i) {
        return i / arcing;
    }

    public int left(int i) {
        return arcing * i;
    }

    public int right(int i) {
        return arcing * i + 1;
    }

    @Override
    public void dump(String caption) {
        System.out.println(caption);
        for (int i = 1; i <= size / 2; i++) {
            System.out.printf(" PARENT : %s LEFT : %d RIGHT :%d\n\r", getElement(i), getElement(left(i)), getElement(right(i)));
        }
        System.out.println();
    }

    @Override
    public void push(T a) {
        heap[++size] = a;
        heapify();
    }

    @Override
    public T pop() {
        T result = getElement(1);
        Helper.swap(heap, 1, size--);
        heapify();
        return result;
    }

    @Override
    public int heapSize() {
        return size;
    }

    @Override
    public T getElement(int i) {
        if (i > size)
            return null;
        return heap[i];
    }

    @Override
    public T[] heap() {
        return heap;
    }
}
