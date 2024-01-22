//쉬운 계단 수
/**
 * [dp]
 * 1. 테이블 정의
 * dp[i][j]: n = i 일 때, j로 끝나는 수의 개수
 * 2. 점화식
 * dp[i][j] = dp[i][4] -> dp[i-1][3] + dp[i-1][5]
 * 3. 초기값
 * dp[0][0-9]
 */
package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10844 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] dp;
    static int n;
    static int WEIGHT = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        init();
        calculate();
        printAnswer();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        dp = new long[n][10];

        dp[0][0] = 0;
        for(int i = 1; i <= 9; i++) {
            dp[0][i] = 1;
        }
    }

    static void calculate() {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i-1][1];
                } else if (j == 9) {
                    dp[i][9] = dp[i-1][8];
                } else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % WEIGHT;
                }
            }
        }
    }

    static void printAnswer() {
        long answer = 0;
        for (int j = 0; j < 10; j++) {
            answer += dp[n-1][j] % WEIGHT;
        }
        System.out.println(answer % WEIGHT);
    }
}
