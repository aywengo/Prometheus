package Prometheus_week4;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String _key = "abcdefghijklmnopqrstuvwxyz";

        File inputFile = new File(String.format("%s\\src\\Prometheus_week4\\%s",
                System.getProperty("user.dir"), "anagrams.txt"));
        ArrayList<String> stringArrayList = Helper.readFile(inputFile.toPath());

        String[] inputAnagrams = stringArrayList.toArray(new String[stringArrayList.size()]);

        RadixSort rs = new RadixSort(_key, inputAnagrams[0].length());

        String[] sorted = rs.sortLSD(inputAnagrams);

        System.out.println("Possible passwords might be:");
        for (char a_key : rs.getMostOccurredChars()) {
            System.out.printf("%s%s%s%n", sorted[0], a_key, sorted[sorted.length - 1]);
        }
    }
}
