//가장 큰 증가하는 부분 수열

/**
 *  [풀이]
 *  dp?
 *  1. 테이블 정의
 *      - dp[k] = K로 끝나는 증가하는 부분 수열의 합 중 최댓값
 *  2. 점화식 도출
 *      - dp[i] = dp[j] + arr[i]
 *  3. 초기값 계산
 *      - 없음
 */

package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11055 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(getMaxValue());
        printTable();
    }

    private static void printTable() {
        System.out.println("print...");
        for(int i = 0; i < n; i++) {
            System.out.println("dp["+i+"]: " + dp[i]);
        }
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
    }

    static int getMaxValue() {
        for(int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}