//피보나치 함수

/*
    1. 그냥 계산한다. -> n == 40: 
    2. 시간초과 -> 따라서, 시간을 줄이는 방법을 생각
    3. 앞의 결과를 이용한다면, 시간을 많이 단축
    4. DP!
    
    1) 테이블 정의하기
        - fibo[i][k]: fibo[i][0] = fibonacci(i)의 k의 개수(k = 0,1)
    2) 점화식 세우기
        - fibo[k][0]: fibo[k-1][0] + fibo[k-2][0];
        - fibo[k][1]: fibo[k-1][1] + fibo[k-2][1];
        - 단, k는 2이상
    3) 초기값 계산
        - fibo[0][0] = 1
        - fibo[0][1] = 0
        - fibo[1][0] = 0
        - fibo[1][1] = 1
 */

package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1003 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int[][] fibo = new int[41][2];


    public static void main(String[] args) throws Exception {
        init();
        while (tc-- != 0) {
            int n = Integer.parseInt(br.readLine());
            save(n);
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        tc = Integer.parseInt(br.readLine());

        fibo[0][0] = 1;
        fibo[0][1] = 0;
        fibo[1][0] = 0;
        fibo[1][1] = 1;

        //테이블 초기화
        for(int i = 2; i <= 40; i++) {
            fibonacci(i);
        }
    }

    static void fibonacci(int n) {
        fibo[n][0] = fibo[n-1][0] + fibo[n-2][0];
        fibo[n][1] = fibo[n-1][1] + fibo[n-2][1];
    }

    static void save(int n) {
        sb.append(fibo[n][0]).append(" ").append(fibo[n][1]).append("\n");
    }
}
