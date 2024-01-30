//동전
/**
 * https://d-cron.tistory.com/23
 */
package Dynamic_Programming.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9084 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int coinType;
    static int[] coins;
    static int goal;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());
        while (tc-- != 0) {
           init();
            for (int coin : coins) {
                for (int i = coin; i < dp.length; i++) {
                    dp[i] = dp[i] + dp[i - coin];
                }
            }
            save();
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        coinType = Integer.parseInt(br.readLine());
        coins = new int[coinType];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < coinType; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        goal = Integer.parseInt(br.readLine());
        dp = new int[goal + 1];
        dp[0] = 1;
    }

    static void save() {
        sb.append(dp[goal]).append("\n");
    }
}
