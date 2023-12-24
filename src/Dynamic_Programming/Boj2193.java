//이친수

/*
    1.테이블 정의
        - D[k][0] = n = k 일 때, 0으로 끝나는 이친수의 개수
        - D[k][1] = n = k 일 때, 1로 끝나는 이친수의 개수
    2.점화식 도출
        - D[k][0] = D[k-1][1] + D[k-1][0]
        - D[k][1] = D[k-1][0]
    3.초기값 계산
        - D[1][0] = 0
        - D[1][1] = 1
 */
package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2193 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static long[][] table; //[1-indexed][0-indexed]
    public static void main(String[] args) throws Exception {
        init();
        dp();
        System.out.println(table[n][0] + table[n][1]);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        table = new long[n+1][2];
        table[1][0] = 0;
        table[1][1] = 1;
    }

    static void dp() {
        for(int i = 2; i <= n; i++) {
            table[i][0] = table[i-1][0] + table[i-1][1];
            table[i][1] = table[i-1][0];
        }
    }
}
