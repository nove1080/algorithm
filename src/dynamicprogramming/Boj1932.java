//정수 삼각형

/*
    풀이
    1. 모든 경우의 수 -> 1*2*4*6*...*n -> 시간초과
    2. 시간단축 방법이 필요
    3. 앞의 결과를 이용해서 해결한다. -> DP

    1. 왼쪽 대각선 -> tri[i][j] = tri[i+1][j]
    2. 오른쪽 대각 -> tri[i][j] = tri[i+1][j+1]

    1. 테이블 정의
        - d[i][j]: i층 j방에서의 최대 누적치

    2. 점화식 도출
        맨 좌측 원소: maxValues[r][c] = maxValues[r-1][c] + triangle[r][c];
        맨 우측 원소: maxValues[r][c] = maxValues[r-1][c-1] + triangle[r][c];
        가운데 원소 : maxValues[r][c] = Math.max(maxValues[r-1][c-1], maxValues[r-1][c]) + triangle[r][c];

    3. 초기값 계산
        maxValues[0][0] = triangle[0][0];
 */
package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1932 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] triangle;
    static int[][] maxValues;

    public static void main(String[] args) throws Exception {
        init();
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                calcMaxValue(i, j);
            }
        }
        int answer = Arrays.stream(maxValues[n-1]).max().getAsInt();
        System.out.println(answer);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        triangle = new int[n][n];
        maxValues = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxValues[0][0] = triangle[0][0];
    }

    //DP
    static void calcMaxValue(int r, int c) {
        if (c == 0) {
            maxValues[r][c] = maxValues[r-1][c];
        } else if (c == r) {
            maxValues[r][c] = maxValues[r-1][c-1];
        } else {
            maxValues[r][c] = Math.max(maxValues[r-1][c-1], maxValues[r-1][c]);
        }
        maxValues[r][c] += triangle[r][c];
    }

}
