package Prometheus_week5;

public class HeapTwister<T extends Comparable<T>> {
    public MinHeap<T> High;
    public MaxHeap<T> Low;

    public HeapTwister(int size) {
        High = new MinHeap<>(2 + size / 2);
        Low = new MaxHeap<>(2 + size / 2);
    }

    public void insert(T item) {
        if (High.getElement(1) == null
                || High.getElement(1).compareTo(item) < 0) {
            High.push(item);
        } else {
            Low.push(item);
        }

        BalanceHeaps();
    }

    public <T> T[] Medians() {
        T[] result = (T[]) new Comparable[2];
        if (hasSizeEven()) {
            result[1] = (T) High.getElement(1);
            result[0] = (T) Low.getElement(1);
        } else {
            if (High.heapSize() > Low.heapSize()) {
                result[0] = (T) High.getElement(1);
            } else {
                result[0] = (T) Low.getElement(1);
            }
        }

        return result;
    }

    private void BalanceHeaps() {
        while (High.heapSize() - Low.heapSize() > 1) {
            Low.push(High.pop());
        }

        while (Low.heapSize() - High.heapSize() > 1) {
            High.push(Low.pop());
        }
    }

    public void dump(String caption) {
        System.out.println(caption);
        System.out.print("Medians ");
        for (int i = 0; i < Medians().length; i++) {
            System.out.print(Medians()[i]);
            System.out.print(" ");
        }
        System.out.println();

        System.out.print("H_low: [");
        for (int i = 1; i <= Low.heapSize(); i++) {
            System.out.printf("%d ", Low.getElement(i));
        }
        System.out.println("]");

        System.out.print("H_high: [");
        for (int i = 1; i <= High.heapSize(); i++) {
            System.out.printf("%d ", High.getElement(i));
        }
        System.out.println("]");

        System.out.println();
    }

    private boolean hasSizeEven() {
        return size() % 2 == 0;
    }

    private int size() {
        return High.heapSize() + Low.heapSize();
    }
}
