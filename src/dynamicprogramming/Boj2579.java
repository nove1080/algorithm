package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2579 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    /**
     * dp[i] = i번째 계단을 마지막으로 간주하였을 때 얻을 수 있는 최고 점수
     * dp[i] = stairs[i] + dp[i-2]
     * dp[i] = stairs[i] + stairs[i-1] + dp[i-3]
     */
    static int[] dp;

    static int[] stairs;


    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp[n - 1]);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        dp = new int[n];
        stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = stairs[0];
        if(n >= 2) dp[1] = stairs[1] + stairs[0];
        if(n >= 3) dp[2] = stairs[2] + Math.max(stairs[0], stairs[1]);

        for (int i = 3; i < n; i++) {
            dp[i] = stairs[i] + Math.max(dp[i - 2], stairs[i - 1] + dp[i - 3]);
        }
    }
}

