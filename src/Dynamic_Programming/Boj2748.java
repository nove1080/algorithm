//피보나치 수 2
package Dynamic_Programming;

import java.io.*;
import java.util.Arrays;

public class Boj2748 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static long[] dp;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(topDown(n));
//        bottomUp();
    }

    private static void bottomUp() {
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
    }

    private static long topDown(int n) {
        if(arr[n] == -1) {
            arr[n] = topDown(n - 1) + topDown(n - 2);
        }
        return arr[n];
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        //bottomUp
        dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        //topDown
        arr = new long[n + 1];
        Arrays.fill(arr, -1);
        arr[0] = 0;
        arr[1] = 1;
    }


}
