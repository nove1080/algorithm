//LCS
/**
 * https://sskl660.tistory.com/90
 */
package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9251 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dp;
    static char[] str1;
    static char[] str2;

    public static void main(String[] args) throws Exception {
        init();
        lcs();
        System.out.println(dp[str2.length - 1][str1.length - 1]);
        printDp();
    }

    static void init() throws Exception {
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        dp = new int[str2.length][str1.length];

        //초기값
        int cnt = 0;
        for (int i = 0; i < str1.length; i++) {
            if (str2[0] == str1[i]) {
                cnt = 1;
            }
            dp[0][i] = cnt;
        }

        cnt = 0;
        for (int i = 0; i < str2.length; i++) {
            if (str1[0] == str2[i]) {
                cnt = 1;
            }
            dp[i][0] = cnt;
        }
    }

    static void lcs() {
        for (int i = 1; i < str2.length; i++) {
            for (int j = 1; j < str1.length; j++) {
                if (str1[j] == str2[i]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    static void printDp() {
        System.out.println("=============");
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
