package Prometheus_week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static ArrayList<String> readFile(java.nio.file.Path file) {
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

    public static Object toObject(Class clazz, String value) {
        if (Boolean.class == clazz || Boolean.TYPE == clazz) {
            return Boolean.parseBoolean(value);
        }
        if (Boolean.class == clazz) return Boolean.parseBoolean(value);
        if (Byte.class == clazz) return Byte.parseByte(value);
        if (Short.class == clazz) return Short.parseShort(value);
        if (Integer.class == clazz) return Integer.parseInt(value);
        if (Long.class == clazz) return Long.parseLong(value);
        if (Float.class == clazz) return Float.parseFloat(value);
        if (Double.class == clazz) return Double.parseDouble(value);
        return value;
    }

    public static <T> List<T> readAndParseFile(java.nio.file.Path file, String splitter) {
        ArrayList<String> stringArrayList = Helper.readFile(file);
        String[] splittedArray = stringArrayList.get(0).split(splitter);

        List<T> list = new ArrayList<>();
        for (String item : splittedArray) {
            list.add((T) toObject(list.getClass(), item));
        }

        return list;
    }
}
