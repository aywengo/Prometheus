package Prometheus_week7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<T> {

    public Node<T> root;

    public void convert(List<T> input) {
        Iterator<T> itr = input.iterator();
        root = inorder(0, input.size() - 1, itr);
    }

    private Node<T> inorder(int lb, int hb, Iterator<T> itr) {
        if (lb > hb) return null;
        // same as (lb+hb)/2, avoids overflow
        int mid = lb + (hb - lb) / 2;

        final Node<T> node = new Node<>();
        node.left = inorder(lb, mid - 1, itr);
        node.item = itr.next();
        node.right = inorder(mid + 1, hb, itr);

        return node;
    }

    public List<T> getInorderList() {
        final List<T> inorderList = new ArrayList<>();
        inorder(root, inorderList);
        return inorderList;
    }

    private void inorder(Node<T> node, List<T> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node.item);
            inorder(node.right, list);
        }
    }

    public static class Node<T> {
        Node<T> left;
        T item;
        Node<T> right;
    }
}
