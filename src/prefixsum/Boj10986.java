package prefixsum;

import java.io.*;
import java.util.*;

public class Boj10986 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static long answer;
    static long[] prefixSum;
    static int[] count; //count[i] = 나머지가 i인 부분합의 개수

    public static void main(String[] args) throws Exception {
        init();
        answer = count[0];
        for (int i = 0; i < m; i++) {
            answer += comb(count[i]);
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        prefixSum = new long[n];
        prefixSum[0] = Integer.parseInt(input[0]);
        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(input[i]);
            prefixSum[i] = prefixSum[i - 1] + num;
        }

        count = new int[m];
        for (int i = 0; i < n; i++) {
            count[(int)(prefixSum[i] % m)]++;
        }
    }

    private static long comb(int n) {
        return (long) n * (n - 1) / 2;
    }
}

