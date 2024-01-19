//가장 긴 증가하는 부분 수열

/**
 * backtracking -> O(n^3) -> 시간 초과
 * DP
 * 1.테이블 정의
 * - dp[k]: k번째 수까지 증가하는 가장 긴 부분 수열의 길이
 * 2.점화식 세우기
 * - dp[i] = dp[j] + 1 ((i,j) = nums[i] > nums[j])
 * 3.초기값 계산
 * - dp[0] = num[0]
 */

package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11053 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(getMaximumLength());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
    }

    static int getMaximumLength() {
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) { //dp[i] 구하기
                if (nums[j] < nums[i]) { //오름차순을 만족하는 경우
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

}
