package Prometheus_week4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class RadixSort_Test {
    String[] _sut;
    RadixSort _rs;
    String _key = "abcdefghijklmnopqrstuvwxyz";

    @Before
    public void initialize() {
        File inputFile = new File(String.format("%s\\src\\Prometheus_week4\\%s",
                System.getProperty("user.dir"), "sample.txt"));
        ArrayList<String> stringArrayList = Helper.readFile(inputFile.toPath());

        _sut = new String[stringArrayList.size()];
        _sut = stringArrayList.toArray(_sut);

        _rs = new RadixSort(_key, _sut[0].length());
    }

    @Test
    public void sortLSD() {
        String[] sorted = _rs.sortLSD(_sut);

        Assert.assertNotNull(sorted);
        Assert.assertTrue(_rs.get_occurrences_amount('a') == 1);
        Assert.assertTrue(_rs.get_occurrences_amount('b') == 0);

        Assert.assertTrue(_rs.get_occurrences_amount('s') == 4);
    }
}
