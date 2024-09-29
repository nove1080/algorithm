//연속합
package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1912 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] numbers;
    static int[] dp; //0-indexed
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(getMaximumValue());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        dp = new int[n];
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = numbers[0];
    }

    static int getMaximumValue() {
        int maxValue = dp[0];
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + numbers[i], numbers[i]);
            if(maxValue < dp[i]) {
                maxValue = dp[i];
            }
        }

        return maxValue;
    }

}
