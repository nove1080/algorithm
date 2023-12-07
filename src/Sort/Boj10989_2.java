package Sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10989_2 {

    // 획기적인 정렬 방법 - 쉽고 좋아보인다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] countArr = new int[10001];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            countArr[num]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < countArr.length; i++) {
            int count = countArr[i];
            for (int j = 0; j < count; j++) {
                // 해당 인덱스의 값이 존재하는 개수만큼 결과값에 추가
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}