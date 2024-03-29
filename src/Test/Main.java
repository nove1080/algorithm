//1654: 랜선 자르기
/*
1. 랜선을 모두 합한다.
2. 최대 길이 = 합한 랜선 길이 / 필요한 랜선 수
3. 각 랜선을 [설정한 길이]로 나눈 몫을 합한 값이 reqCables와 같은지 확인
4. 적다 -> 설정한 길이--
5. 많다 -> 설정한 길이++
6. 더 늘리면 reqCables를 충족하지 못할 때까지 (4 or 5) -> 3을 반복
 */
package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ownCables, reqCables;
    static int[] cables;
    static long maxHeight;

    public static void main(String[] args) throws Exception {
        init();
        searchCableLength(1, maxHeight);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        ownCables = Integer.parseInt(st.nextToken());
        reqCables = Integer.parseInt(st.nextToken());

        cables = new int[ownCables];

        int sum = 0;
        for (int i = 0; i < ownCables; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            sum += cables[i];
        }

        maxHeight = sum / reqCables;
    }

    static void searchCableLength(long start, long end) {
        long result = 0;
        while (start <= end) {
            long height = (start + end) / 2;
            long quantity = 0;
            for (int i = 0; i < cables.length; i++) {
                quantity += cables[i] / height;
            }

            if (quantity < reqCables) {
                end = height - 1;
            } else {
                start = height + 1;
                result = Math.max(height, result);
            }
        }

        System.out.println(result);
    }
}