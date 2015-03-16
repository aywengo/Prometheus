package Prometheus_week7;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week7\\samples\\%s",
                System.getProperty("user.dir"), "input_10a.txt"));
        List<Integer> input = Helper.readAndParseFile(inputFile.toPath(), " ");

        BinarySearchTree<Integer> s = new BinarySearchTree<>();
        s.convert(input);

        System.out.println("Root " + s.root.item);
        System.out.println();

        System.out.println("First 3 leafs :");
        List<Integer> inorderList = s.getInorderList();
        for (int i = 0; i < 3; i++) {
            System.out.print(inorderList.get(i) + " ");
        }

        System.out.println();

        System.out.println("Last 3 leafs :");
        for (int i = inorderList.size() - 1; i >= inorderList.size() - 3; i--) {
            System.out.print(inorderList.get(i) + " ");
        }
        //System.out.println(input.get(0));
    }
}
