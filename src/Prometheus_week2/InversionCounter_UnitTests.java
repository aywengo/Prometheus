package Prometheus_week2;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class InversionCounter_UnitTests {

    @Test
    public void sortAndCountInv_test() {
        int[] input = {5, 2, 4, 3, 1};
        int c = InversionCounter.sortAndCountInv(input);
        assertEquals(8, c);
    }

    @Test
    public void sortAndCountInv_test1() {
        int[] input = {2, 5, 4, 3, 1};
        int c = InversionCounter.sortAndCountInv(input);
        assertEquals(7, c);
    }

    @Test
    public void compareAndMerge_test() {
        int[] l = {5, 4, 1, 2, 3};
        int[] r = {1, 3, 5, 2, 4};
        int[] expected = {5, 2, 4, 3, 1};

        int[] c = InversionCounter.compareAndMerge(l, r);

        assertArrayEquals(expected, c);
    }
}
