//1로 만들기 2

/*
    1. 테이블 정의하기
        D[i]: i를 1로 만드는 최소한의 연산횟수

        D[1]: 0
        D[2]: 1
        D[3]: 1
        D[4]: 2 (D[2] + 1)
        D[5]: 3 (D[4] + 1)

    2. 점화식 찾기
        D[k] = min(D[k/3], D[k/2], D[k-1]) + 1;
        k -> /3, /2가 가능하다면 저렇게
    3. 초기값 계산
 */

package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj12852 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] numbers; //1-indexed
    static int[] route;

    public static void main(String[] args) throws Exception {
        init();
        dp();
        printAnswer();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        numbers = new int[n + 1];
        route = new int[n + 1];
        route[1] = 0;
        numbers[1] = 0;
    }

    static void dp() {
        for (int i = 2; i <= n; i++) {
            int three = Integer.MAX_VALUE;
            int two = Integer.MAX_VALUE;
            int one = numbers[i - 1];
            if(i % 3 == 0) three = numbers[i / 3];
            if(i % 2 == 0) two = numbers[i / 2];

            if(two >= three) {
                if(three < one) {
                    numbers[i] = three + 1;
                    route[i] = i / 3;
                } else {
                    numbers[i] = one + 1;
                    route[i] = i - 1;
                }
            } else {
                if(two <= one) {
                    numbers[i] = two + 1;
                    route[i] = i / 2;
                }
                else {
                    numbers[i] = one + 1;
                    route[i] = i - 1;
                }
            }
        }
    }

    static void printAnswer() {
        sb.append(numbers[n]).append("\n");
        sb.append(n).append(" ");

        int k = n;
        while(route[k] != 0) {
            sb.append(route[k]).append(" ");
            k = route[k];
        }
        System.out.println(sb);
    }
}
