package Prometheus_week1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {

        BigInteger x = new BigInteger("1685287499328328297814655639278583667919355849391453456921116729");
        BigInteger y = new BigInteger("7114192848577754587969744626558571536728983167954552999895348492");

        HashMap<Integer, Integer> statistic = new HashMap<Integer, Integer>();
        statistic.put(105, 0);
        statistic.put(72, 0);
        statistic.put(12, 0);

        AtomicReference<HashMap<Integer, Integer>> rs = new AtomicReference<HashMap<Integer, Integer>>(statistic);

        BigInteger xy = Karatsuba.multiply(x, y, rs);
        System.out.println(xy);

        for (Map.Entry<Integer, Integer> entry : rs.get().entrySet()) {
            System.out.println(entry.getKey() + " <==> " + entry.getValue());
        }
    }
}



