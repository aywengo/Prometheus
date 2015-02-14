package Prometheus_week1;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static junit.framework.Assert.assertEquals;

public class Karatsuba_Test {

    @Test
    public void sortAndCountInv_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();
        statistic.put(7, -1);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("1234", "5678", rs);
    }

    @Test
    public void sortAndCountInvUnEven_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("12345", "6789", rs);
    }

    @Test
    public void sortAndCountInvSample1_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();
        statistic.put(7, -2);
        statistic.put(8, -3);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("49823261", "44269423", rs);
    }

    @Test
    public void sortAndCountInvSample2_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();
        statistic.put(5, -2);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("54761293", "65394884", rs);
    }

    @Test
    public void sortAndCountInvSample3_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();
        statistic.put(18, -1);
        statistic.put(9, -2);
        statistic.put(8, -6);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("9313685456934674", "7658898761837539", rs);
    }

    @Test
    public void sortAndCountInvSample4_test() {
        HashMap<Integer, Integer> statistic = new HashMap<>();
        statistic.put(64, -5);
        statistic.put(60, -3);
        statistic.put(58, -5);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<>(statistic);
        calcTest("8711129198194917883527844183686122989894424943636426448417394566",
                "2924825637132661199799711722273977411715641477832758942277358764", rs);
    }

    private void calcTest(String a, String b, AtomicReference<HashMap<Integer, Integer>> st) {
        BigInteger x = new BigInteger(a);
        BigInteger y = new BigInteger(b);
        BigInteger result = x.multiply(y);

        BigInteger z = Karatsuba.multiply(x, y, st);

        assertEquals(result, z);

        for (Map.Entry<Integer, Integer> entry : st.get().entrySet()) {
            assertEquals("0", entry.getValue().toString());
        }
    }
}
