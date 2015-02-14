package Prometheus_week2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // analyse("test_5_5.txt", 1, 3, false);

        // analyse("test_50_100.txt", 48, 50, false);
        // analyse("test_50_100.txt", 18, 1, false);

        // analyse("input_1000_5.txt", 452, 100, false);
        // analyse("input_1000_5.txt", 863, 29, false);

        analyse("input_1000_100.txt", 618, 1, false);
        analyse("input_1000_100.txt", 951, 178, false);
    }

    private static void analyse(String file, int user1, int user2, boolean readAll) {
        try {
            File f = new File(String.format("%s\\src\\Prometheus_week2\\%s", System.getProperty("user.dir"), file));

            ArrayList<String> input = readFile(f.toPath());
            String[] us_films = input.get(0).split("[ ]+");

            int usersAmount = Integer.parseInt(us_films[0]);
            int ratedFilmsAmount = Integer.parseInt(us_films[1]);
            System.out.printf("Analysing preferences of %s users to %s films%n", usersAmount, ratedFilmsAmount);

            int[][] rating = new int[usersAmount + 1][ratedFilmsAmount];
            for (int i = 1; i < input.size(); i++) {
                String[] line = input.get(i).split("[ ]+");
                int userId = Integer.parseInt(line[0]);

                if (!readAll && user1 != userId && user2 != userId)
                    continue;

                System.out.printf(String.format("Parsing rates for user# %d%n", userId));

                for (int k = 0; k < ratedFilmsAmount; k++) {
                    rating[userId][k] = Integer.parseInt(line[k + 1]);
                }
            }

            int[] a = InversionCounter.compareAndMerge(rating[user1], rating[user2]);
            int result = InversionCounter.sortAndCountInv(a);

            System.out.printf(String.format("Amount of conversion between rates of user# %d and user# %d is %d%n%n", user1, user2, result));
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.exit(-1);
        }
    }

    private static ArrayList<String> readFile(java.nio.file.Path file) {
        ArrayList<String> result = new ArrayList<>();
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        return result;
    }

}
