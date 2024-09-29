//1,2,3 더하기
package dynamicprogramming;

import java.io.*;

/*
    DP
    1. 테이블 정의하기
     - D[i]: i를 1,2,3의 합으로 나타내는 방법의 수
    2. 점화식 찾기
     - D[i] = D[i-1] + D[i-2] + D[i-3]
     - ex) D[4]
     -      D[1] + '3' -> 4가 된다.
     -      D[2] + '2' -> 4가 된다.
     -      D[3] + '1' -> 4가 된다.
    3. 초기값 정하기
     - D[1], D[2], D[3]
 */


public class Boj9095 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] answers = new int[12];

    public static void main(String[] args) throws Exception {
        dp();
        System.out.println(sb);
    }

    static void dp() throws Exception {
        n = Integer.parseInt(br.readLine());

        //초기값
        answers[1] = 1;
        answers[2] = 2;
        answers[3] = 4;

        //점화식 적용
        for(int i = 4; i <= 11; i++) {
            answers[i] = answers[i - 1] + answers[i - 2] + answers[i - 3];
        }

        for (int i = 0; i < n; i++) {
            int tc = Integer.parseInt(br.readLine());
            sb.append(answers[tc]).append("\n");
        }
    }

}
