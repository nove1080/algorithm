//퇴사
/**
 * [풀이]
 * 1. DP
 * https://january-diary.tistory.com/entry/BOJ-14501-%ED%87%B4%EC%82%AC-JAVA
 */

package dynamicprogramming.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14501 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int day;
    static int[] times;
    static int[] prices;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(getMaximumRevenue());
        printDp();
    }

    static void init() throws Exception {
        day = Integer.parseInt(br.readLine());

        times = new int[day];
        prices = new int[day];
        dp = new int[day];

        for (int i = 0; i < day; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken()) - 1;
            prices[i] = Integer.parseInt(st.nextToken());
        }

        //초기값
        if(times[0] == 0) dp[0] = prices[0];
    }

    static int getMaximumRevenue() {
        for (int i = 1; i < day; i++) {
            for (int j = 1; j <= i; j++) {
                if(j + times[j] <= i) { //퇴사일까지 끝낼 수 있는 일
                    dp[i] = Math.max(dp[i], dp[j - 1] + prices[j]);
                }
            }
        }

        return dp[day - 1];
    }

    static void printDp() {
        System.out.println("===============");
        for (int i = 0; i < day; i++) {
            System.out.println(i + ": " + dp[i]);
        }

    }
}


