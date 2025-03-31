package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Boj10826 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    static BigInteger[] result = new BigInteger[10001];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(result[n]);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        Arrays.fill(result, BigInteger.valueOf(-1));
        result[0] = BigInteger.ZERO;
        result[1] = BigInteger.ONE;

        for (int i = 2; i < 10001; i++) {
            result[i] = result[i - 1].add(result[i - 2]);
        }
    }
}
