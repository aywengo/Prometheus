package Prometheus_week3;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week3\\%s",
                System.getProperty("user.dir"), "input__10000.txt"));

        System.out.printf(Helper.countComparisons(inputFile, PivotElement.LAST, "Amount of comparisons of QuickSort algorithm by choosing last element of array is %d%n"));
        System.out.printf(Helper.countComparisons(inputFile, PivotElement.FIRST, "Amount of comparisons of QuickSort algorithm by choosing first element of array is %d%n"));
        System.out.printf(Helper.countComparisons(inputFile, PivotElement.MEDIANA, "Amount of comparisons of QuickSort algorithm by calculating median is %d%n"));
    }
}
