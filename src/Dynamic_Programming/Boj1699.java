//제곱수의 합

/**
 https://chanhuiseok.github.io/posts/baek-10/
 */
package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1699 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] dp; //1-indexed
    public static void main(String[] args) throws Exception {
        init();
        solve();
        System.out.println(dp[n]);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
    }

    static void solve() {
        for (int i = 0; i <= n; i++) {
            dp[i] = i; //초기값
            for (int j = 1; j <= Math.sqrt(i); j++) {
                int num = (int) (i - Math.pow(j, 2));
                dp[i] = Math.min(dp[i], dp[num] + 1);
            }
        }
    }

    static void printDp() {
        System.out.println("=========");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + ": " + dp[i]);
        }
    }
}
