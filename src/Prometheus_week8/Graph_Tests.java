package Prometheus_week8;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Graph_Tests {
    @Test
    public void test1() {
        test("test_08_1");
    }

    @Test
    public void test2() {
        test("test_08_2");
    }

    @Test
    public void test3() {
        test("test_08_3");
    }

    @Test
    public void test4() {
        test("test_08_4");
    }

    private void test(String fileName) {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week8\\testData\\%s",
                System.getProperty("user.dir"), fileName + ".txt"));

        File outputFile = new File(String.format("%s\\src\\Prometheus_week8\\testData\\%s",
                System.getProperty("user.dir"), fileName + ".output.txt"));

        Assert.assertEquals(readOneLineFile(outputFile.toPath()),
                Main.computePattern(inputFile));
    }

    private static String readOneLineFile(java.nio.file.Path file) {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            return reader.readLine();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return null;
    }
}