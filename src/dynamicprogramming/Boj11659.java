//구간 합 구하기 4
package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Boj11659 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[] sums; //idx까지 더한 합

    public static void main(String[] args) throws Exception {
        init();
        while(m-- != 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            save(dp(i, j));
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sums = new int[n];
        sums[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < n; i++) {
            sums[i] = sums[i-1] + Integer.parseInt(st.nextToken());
        }
    }

    static int dp(int i, int j) {
        if(i > 1)
            return sums[j - 1] - sums[i - 2];
        else
            return sums[j - 1];
    }

    static void save(int sum) {
        sb.append(sum).append("\n");
    }
}
