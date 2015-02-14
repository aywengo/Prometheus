package Prometheus_week2;

import java.util.Arrays;

public class InversionCounter {
    public static int[] compareAndMerge(int[] L, int[] R) {
        int[] A = new int[L.length];
        int[] temp = new int[L.length];
        System.arraycopy(L, 0, temp, 0, temp.length);
        Arrays.sort(temp);

        for (int i = 0; i < temp.length; i++) {
            int ind = lookingForValue(L, temp[i]);
            if (ind >= 0) {
                A[i] = R[ind];
            }
        }
        return A;
    }

    public static int sortAndCountInv(int[] A) {
        if (A.length == 1)
            return 0;

        int n = A.length / 2;

        int[] L = new int[n];
        int[] R = new int[A.length - n];

        System.arraycopy(A, 0, L, 0, n);
        System.arraycopy(A, n, R, 0, R.length);

        int x = sortAndCountInv(L);
        int y = sortAndCountInv(R);
        int z = mergeAndCountSplitInv(A, L, R);

        return x + y + z;
    }

    private static int mergeAndCountSplitInv(int[] a, int[] l, int[] r) {
        int j = 0;
        int i = 0;
        int c = 0;

        for (int k = 0; k < (l.length + r.length); k++) {
            if ((j >= r.length) || (i < l.length && l[i] <= r[j])) {
                a[k] = l[i];
                i++;
            } else {
                a[k] = r[j];
                j++;
                c = c + (l.length - i);
            }
        }

        return c;
    }

    private static int lookingForValue(int[] input, int value) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == value)
                return i;
        }

        return -1;
    }
}
