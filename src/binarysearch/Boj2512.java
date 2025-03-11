//예산
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2512 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;

    static int budget;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(searchMaximumBudget());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        budget = Integer.parseInt(br.readLine());
    }

    public static int searchMaximumBudget() {
        int st = 1;
        int en = budget;

        int result = 0;
        while (st <= en) {
            int tempBudget = budget;
            int mid = (st + en) / 2;

            int count = 0;
            //최대 mid 만큼 예산액 분배하기
            for (int i = 0; i < n; i++) {
                if (arr[i] < mid && tempBudget >= arr[i]) { //요청예산액 < mid
                    tempBudget -= arr[i];
                    count++;
                } else if (arr[i] >= mid && tempBudget >= mid) { //요청예산액 >= mid
                    tempBudget -= mid;
                    count++;
                }
            }

            //예산을 모두 주지 못한 경우
            if (count < n) en = mid - 1;

            //예산을 모두 배정한 경우
            else {
                st = mid + 1;
                result = Math.max(result, mid);
            }
        }

        // 예산 요청액 만큼 모두 다 줄 수 있을 때
        if (result == budget) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, arr[i]);
            }
            result = max;
        }

        return result;
    }
}
