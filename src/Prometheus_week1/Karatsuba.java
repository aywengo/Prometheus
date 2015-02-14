package Prometheus_week1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Karatsuba {
    public static BigInteger multiply(BigInteger x, BigInteger y, AtomicReference<HashMap<Integer, Integer>> st) {

        int N, base = 10;
        if (x.compareTo(BigInteger.valueOf(base)) <= 0 &&
                y.compareTo(BigInteger.valueOf(base)) <= 0) {
            return x.multiply(y);
        }

        if (x.compareTo(y) == 1)
            N = x.toString().length();
        else
            N = y.toString().length();

        int n;
        if (N % 2 == 0) {
            n = N / 2;
        } else {
            n = ++N / 2;
        }

        // x = 2^N a +  b,   y = 2^N c + d
        BigInteger a = x.divide(BigInteger.valueOf(base).pow(n));
        BigInteger b = x.mod(BigInteger.valueOf(base).pow(n));
        BigInteger c = y.divide(BigInteger.valueOf(base).pow(n));
        BigInteger d = y.mod(BigInteger.valueOf(base).pow(n));

        // compute sub-expressions
        BigInteger ac = multiply(a, c, st);
        BigInteger bd = multiply(b, d, st);

        BigInteger ad_bc = multiply(b.add(a), d.add(c), st).subtract(bd).subtract(ac);
        stat(ad_bc, st);

        // sum-up result of the multiplication
        BigInteger result =
                ac.multiply(BigInteger.valueOf(base).pow(N))
                        .add(ad_bc.multiply(BigInteger.valueOf(base).pow(n)))
                        .add(bd);

        return result;
    }

    public static void stat(BigInteger a, AtomicReference<HashMap<Integer, Integer>> stat) {
        HashMap<Integer, Integer> st = stat.get();

        if (st.containsKey(a.intValue())) {
            st.put(a.intValue(), st.get(a.intValue()) + 1);
        }

        stat.set(st);
    }

}
