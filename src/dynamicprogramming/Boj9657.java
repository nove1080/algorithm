package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9657 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(simulate() == 0 ? "SK" : "CY");
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        dp[1] = 0;
        if(n >= 2) dp[2] = 1;
        if(n >= 3) dp[3] = 0;
        if(n >= 4) dp[4] = 0;
    }

    public static int simulate() {
        for (int i = 5; i < n + 1; i++) {
            if(dp[i - 1] == 1 || dp[i - 3] == 1 || dp[i - 4] == 1) dp[i] = 0;
            else dp[i] = 1;
        }

        return dp[n];
    }

}
