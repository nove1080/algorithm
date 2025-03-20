//공유기 설치
/**
 * 이분탐색으로 접근하게 되는 근거
 *
 *
 * "어떠한 조건을 만족하도록 하는 어떤 값을 최대화 혹은 최소화하라"는 문제에서 떠올려볼 수 있는 테크닉입니다.
 * 이런 문제를 만났을 때, '최댓값 혹은 최솟값을 직접 구하는 대신, 이 값이 특정 값으로 주어졌다고 쳤을 때 조건을 만족하는 지를 적절한 시간 복잡도 내에 구하는 것이 가능할까?'를 한번 생각해보고,
 * 이것이 가능하다면 조건을 만족하는 값의 경계선을 이분 탐색으로 구하는 것이 가능하다고 생각할 수 있습니다.
 *
 * 파라메트릭 서치
 * 최적화 문제 -> 결정 문제로 바꾸는 것
 *
 * [출처]
 * - https://www.acmicpc.net/board/view/155334
 */
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2110 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, c;
    static int[] homes;

    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        homes = new int[n];
        for (int i = 0; i < n; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void binarySearch(int target) {

    }
}
