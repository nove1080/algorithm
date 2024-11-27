//가장 긴 짝수 연속한 부분 수열 (large)
package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj22862 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(twoPointer());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        arr = new int[n];

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }

    public static int twoPointer() {
        int result = 0;

        List<Integer> oddIndexes = new ArrayList<>();
        int st = 0;
        int cnt = 0; //홀수의 개수
        int totalOddCount = 0;
        for (int end = 0; end < arr.length; end++) {
            System.out.println("currentNumber = " + arr[end] + ", current index = (" + st + " , " + end + ")");
            if (arr[end] % 2 == 1) {
                cnt++;
                totalOddCount++;
                System.out.println("cnt = " + cnt);
                System.out.println("totalOddCount = " + totalOddCount);
                oddIndexes.add(end);
            }
            //st를 움직일 때 cnt--
            if (cnt == k + 1 || end == arr.length - 1) {
                //길이 저장
                result = Math.max(end - st - cnt + 1, result);
                System.out.println("[SAVE] " + result);
                //st 적절히 움직이기
                if (totalOddCount - k - 1 >= 0) {
                    st = oddIndexes.get(totalOddCount - k - 1) + 1;
                }
                System.out.println("next [st] Idx = " + st);
                cnt--;
            }
        }

        return result;
    }

}
