//RGB거리

/*
    1. 테이블 정의하기
        - D[i][0-2]: i번째 집을 (0-2)의 색상으로 칠할 때, 누적된 최소한의 비용
    2. 점화식 도출
        - D[i][0] = min(D[i-1][1], D[i-1][2]) + cost[i][0]
        - D[i][1] = min(D[i-1][0], D[i-1][2]) + cost[i][1]
        - D[i][2] = min(D[i-1][0], D[i-1][1]) + cost[i][2]
    3. 초기값 설정
 */
package Dynamic_Programming;

import java.io.*;
import java.util.*;

public class Boj1149 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] costs; //각 집의 도색 비용

    public static void main(String[] args) throws Exception {
        //0-indexed
        init();
        System.out.println(dp());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        costs = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                costs[i][0] = Integer.parseInt(st.nextToken());
                costs[i][1] = Integer.parseInt(st.nextToken());
                costs[i][2] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int dp() {
        for (int i = 1; i < n; i++) {
            costs[i][0] = Math.min(costs[i - 1][1], costs[i - 1][2]) + costs[i][0];
            costs[i][1] = Math.min(costs[i - 1][0], costs[i - 1][2]) + costs[i][1];
            costs[i][2] = Math.min(costs[i - 1][0], costs[i - 1][1]) + costs[i][2];
        }

        return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
    }
}
