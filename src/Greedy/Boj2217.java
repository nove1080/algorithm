//로프
/**
 * 풀이
 * 1. ropes를 정렬 후 각 원소 하나하나 보면서 계산하기 -> 시간초과
 * 2. 전체 문제를 똑같은 부분 문제로 나눌 수 있음 -> DP
 *  - DP는 Memoization을 사용하여 시간 단축이 가능할 것으로 보임
 *  - DP가 아니라 Memoization만 사용해도 할 수 있을 것 같다.
 *  - 내림차순 정렬
 *  -
 */
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Boj2217 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Integer[] ropes;
    public static void main(String[] args) throws Exception {
        init();
        int maxWeight = 0;
        for (int i = 0; i < n; i++) {
            int weight = ropes[i] * (i+1);
            if(maxWeight < weight)
                maxWeight = weight;
        }

        System.out.println(maxWeight);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        ropes = new Integer[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes, Collections.reverseOrder());
    }

}
