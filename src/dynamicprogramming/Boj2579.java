//계단 오르기

/*
    1. 테이블 정의하기
        D[i][j] = i번째 계단에서 j만큼 연속해서 계단을 올랐을 때, 점수 합의 최대값
    2. 점화식 찾기
        D[k][1] = max(D[k-2][2], D[k-2][1]) + score[k]
        D[k][2] = D[k-1][1] + score[k]
    3. 초기값 정하기
        D[1][1]
 */

/*
    풀이 2
       1. 테이블 정의하기
        D[1] = score[1], D[2] = score[2],
        D[3] = D[1] + score[3],
        D[4] =

       2. 점화식 찾기
        * k번째 안 밟는다 -> k-1번째는 반드시 밟는다., k-2는 반드시 안 밟는다? *
        D[k] = D[k-2] + score[k]

        if (k == n) then

       3. 테이블 채우기
         D[1] = 10, D[2] = 20, D[3] = 15,
         D[4] = 25 + min(s[k-2], s[k-3]) (35);
         D[5] = 10 + min(s[k-2], s[k-3]) (25);

*/
package dynamicprogramming;

import java.io.*;

public class Boj2579 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] stairs;
    static int[][] scores; //각 계단(idx)에서의 최대 점수

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        stairs = new int[n+1];
        scores = new int[n+1][3];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if(n >= 1) {
            scores[1][1] = stairs[1];
            scores[1][2] = 0;
        }

        if(n >= 2) {
            scores[2][1] = stairs[2];
            scores[2][2] = stairs[1] + stairs[2];
        }
    }

    static int dp() {
        for(int i = 3; i <= n; i++) {
            scores[i][1] = Math.max(scores[i - 2][2], scores[i - 2][1]) + stairs[i];
            scores[i][2] = scores[i - 1][1] + stairs[i];
        }
        return Math.max(scores[n][1], scores[n][2]);
    }
}
