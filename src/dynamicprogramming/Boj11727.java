//2xn 타일링 2
package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    [DP]
    1. 테이블 정의
    2. 점화식 추출
    3. 초기값 계산
 */
public class Boj11727 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;

    static int[] table; // 1-indexed

    public static void main(String[] args) throws Exception {
        init();
        dp(n);
        System.out.println(table[n]);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        table = new int[n + 1];
        table[1] = 1;
        if(n > 1)
            table[2] = 3;
    }

    static void dp(int n) {
        for (int i = 3; i <= n; i++) {
            table[i] = (table[i-1] + 2 * table[i-2]) % 10007;
        }
    }
}
