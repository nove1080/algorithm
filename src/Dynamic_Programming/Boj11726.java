//2xn 타일링

/*
    1. 테이블 정의하기
        - D[i] = 2xi 크기의 직사각형을 채우는 방법의 수

    2. 점화식 찾기
        - D[k] = D[k-1] + D[k-2]

    3. 초기값 계산
        - D[1] = 1, D[2] = 2
 */

package Dynamic_Programming;

import java.io.*;
import java.util.*;

public class Boj11726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] cases;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        cases = new int[n + 1];
        cases[1] = 1;
        if (n >= 2)
            cases[2] = 2;
    }

    static int dp() {
        for (int i = 3; i <= n; i++) {
            cases[i] = (cases[i - 1] + cases[i - 2]) % 10007;
        }
        return cases[n];
    }

}
