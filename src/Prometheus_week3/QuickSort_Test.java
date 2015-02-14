package Prometheus_week3;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QuickSort_Test {

    @Test
    public void testSort10Elements() {
        compareInputOutput("input__10.txt", "output__10.txt");
    }

    @Test
    public void testSort100Elements() {
        compareInputOutput("input__100.txt", "output__100.txt");
    }

    @Test
    public void testSort1000Elements() {
        compareInputOutput("input__1000.txt", "output__1000.txt");

    }

    private void compareInputOutput(String in, String out) {
        File input = new File(String.format("%s\\src\\Prometheus_week3\\%s", System.getProperty("user.dir"), in));

        File outputArray = new File(String.format("%s\\src\\Prometheus_week3\\%s", System.getProperty("user.dir"), out));
        ArrayList<String> output = Helper.readFile(outputArray.toPath());

        String outputted = String.format("%s %s %s",
                Helper.countComparisons(input, PivotElement.LAST, "%d"),
                Helper.countComparisons(input, PivotElement.FIRST, "%d"),
                Helper.countComparisons(input, PivotElement.MEDIANA, "%d")
        );

        assertEquals(output.get(0), outputted);
    }
}