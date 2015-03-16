package Prometheus_week7;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BinarySearchTree_Tests {
    @Test
    public void testOne() {
        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        s.convert(list1);
        assertEquals(list1, s.getInorderList());
    }

    @Test
    public void testTwo() {
        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        s.convert(list2);
        assertEquals(list2, s.getInorderList());
    }

    @Test
    public void testThree() {
        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6);
        s.convert(list3);
        assertEquals(list3, s.getInorderList());
    }

    @Test
    public void testFour() {
        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        List<Integer> list4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        s.convert(list4);
        assertEquals(list4, s.getInorderList());
    }

}
