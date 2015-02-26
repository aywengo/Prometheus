package Prometheus_week5;

interface IHeap {
    int parent(int i);

    int heapSize();

    int getElement(int i);

    int[] heap();
}

interface IBinaryHeap extends IHeap {
    int left(int i);

    int right(int i);
}

interface IMaxBinaryHeap extends IBinaryHeap {

}

public abstract class BinaryHeap extends HeapBase implements IMaxBinaryHeap {

    protected int heapDegree = 2;

    @Override
    public int left(int i) {
        return heapDegree * i;
    }

    @Override
    public int right(int i) {
        return heapDegree * i + 1;
    }


}

