package Prometheus_week4;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class RadixSort {
    private char[] _key;
    private int _max;
    private int _rad;
    private int[][] _counts;
    private Dictionary<Character, Integer> _occurrences;

    public RadixSort(String key, int rad) {
        _key = key.toCharArray();
        _rad = rad;
        _max = 0;
        _counts = new int[_rad][_key.length];
    }

    public char get_value(int index) {
        return _key[index];
    }

    public int get_key(char character) {
        for (int i = 0; i < _key.length; i++) {
            if (_key[i] == character)
                return i;
        }

        return -1;
    }

    public String[] sortLSD(String[] input) {
        String[][] result = new String[_rad + 1][input.length];
        result[_rad] = input;

        for (int j = _rad - 1; j >= 0; j--) {
            for (String anInput : result[j + 1]) {
                char ch = anInput.charAt(j);
                int k = get_key(ch);
                _counts[j][k]++;
            }

            int[] c = calculate(j);

            for (int i = result[j + 1].length - 1; i >= 0; i--) {
                char ch = result[j + 1][i].charAt(j);
                int k = get_key(ch);
                int index = c[k]--;
                result[j][index - 1] = result[j + 1][i];
            }
        }

        ArrangeOccurrences();

        System.out.printf("The first line after FIRST iteration is %s%n", result[_rad - 1][0]);
        System.out.printf("The first line after SECOND iteration is %s%n", result[_rad - 2][0]);

        return result[0];
    }

    private void ArrangeOccurrences() {
        _occurrences = new Hashtable<>();
        _max = 0;

        for (int i = 0; i < _key.length; i++) {
            int count = 0;
            for (int j = 0; j < _rad; j++) {
                count += _counts[j][i];
            }

            if (count > _max)
                _max = count;

            _occurrences.put(get_value(i), count);
        }
    }

    private int[] calculate(int rad) {
        int[] calculations = new int[_key.length];
        calculations[0] = _counts[rad][0];
        for (int i = 1; i < _key.length; i++) {
            calculations[i] = _counts[rad][i] + calculations[i - 1];
        }

        return calculations;
    }

    public int get_occurrences_amount(char ch) {
        return _occurrences.get(ch);
    }

    public List<Character> getMostOccurredChars() {
        List<Character> result = new ArrayList<>();

        for (char a_key : _key) {
            if (_occurrences.get(a_key) >= _max) {
                result.add(a_key);
            }
        }

        return result;
    }
}
