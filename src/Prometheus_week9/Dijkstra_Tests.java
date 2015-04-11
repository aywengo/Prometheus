package Prometheus_week9;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Dijkstra_Tests {
    @Test
    public void test1() {
        assertInOuts("input_1_100.txt", "output_1.txt");
    }

    @Test
    public void test2() {
        assertInOuts("input_2_100.txt", "output_2.txt");
    }

    @Test
    public void test3() {
        assertInOuts("input_3_100.txt", "output_3.txt");
    }

    @Test
    public void test4() {
        assertInOuts("input_4_1000.txt", "output_4.txt");
    }

    @Test
    public void test5() {
        assertInOuts("input_5_10.txt", "output_5.txt");
    }

    @Test
    public void test6() {
        assertInOuts("input_6_10.txt", "output_6.txt");
    }

    @Test
    public void test7() {
        assertInOuts("input_7_10.txt", "output_7.txt");
    }

    @Test
    public void test8() {
        assertInOuts("input_8_10.txt", "output_8.txt");
    }


    private void assertInOuts(String inputFile, String outputFile) {
        try {
            Graph<Integer> graph = Helper.getGraphFromFile(inputFile);
            int[][] expected = Helper.parseRelationMatrix(outputFile, graph.Vertexes.size());
            int[][] relationMatrix = graph.getRelationMatrix();
            Assert.assertArrayEquals(expected, relationMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
