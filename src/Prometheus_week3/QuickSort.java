package Prometheus_week3;

public class QuickSort {

    public static int sortByLastElementAsPivot(int[] A, int p, int r, int[] c) {
        if (p < r) {
            int q = partition(A, p, r, c);
            sortByLastElementAsPivot(A, p, q - 1, c);
            sortByLastElementAsPivot(A, q + 1, r, c);

            return c[0];
        }
        return 0;
    }

    public static int sortByMedianElementAsPivot(int[] A, int p, int r, int[] c) {
        if (p < r) {
            int median = median(A, p, r);
            swap(A, median, r);

            int q = partition(A, p, r, c);

            return (r - p)
                    + sortByMedianElementAsPivot(A, p, q - 1, c)
                    + sortByMedianElementAsPivot(A, q + 1, r, c);
        }
        return 0;
    }

    public static int sortByFirstElementAsPivot(int[] A, int p, int r, int[] c) {
        if (p < r) {
            swap(A, p, r);
            int q = partition(A, p, r, c);

            return (r - p)
                    + sortByFirstElementAsPivot(A, p, q - 1, c)
                    + sortByFirstElementAsPivot(A, q + 1, r, c);
        }
        return 0;
    }

    public static int median(int[] A, int p, int r) {
        int c = (p + r) / 2;

        if (A[p] < A[r]) {
            if (A[c] < A[p]) {
                return p;
            } else if (A[c] < A[r]) {
                return c;
            } else {
                return r;
            }
        } else if (A[r] < A[p]) {
            if (A[c] < A[r]) {
                return r;
            } else if (A[c] < A[p]) {
                return c;
            } else {
                return p;
            }
        }

        return p;
    }

    public static int partition(int[] A, int p, int r, int[] counter) {
        int x = A[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            counter[0]++;
            if (A[j] <= x) {
                swap(A, ++i, j);
            }
        }
        swap(A, r, ++i);

        return i;
    }

    public static void swap(int[] A, int x, int y) {
        int z = A[x];
        A[x] = A[y];
        A[y] = z;
    }
}