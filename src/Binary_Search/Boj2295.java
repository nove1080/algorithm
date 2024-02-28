//세 수의 합
//어느 한 쪽의 값을 계산하여 이분 탐색을 시켜 시간복잡도를 낮추는 아이디어는 종종 쓰인다.
//a[x] + a[y] + a[z] = a[k]
//two[i] = a[x] + a[y]
//two[i] = a[k] - a[z]
//를 만족하는 값이 있는지 two 배열을 이분 탐색 -> O(n^2lgn)
package Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2295 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr;
    static int[] twoNumsSum;

    public static void main(String[] args) throws Exception {
        init();
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int result = Arrays.binarySearch(twoNumsSum, arr[j] - arr[i]);
                if (result >= 0) {
                    max = Math.max(arr[j], max);
                }
            }
        }

        System.out.println(max);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        int twoNumsSize = n;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            twoNumsSize += i;
        }
        Arrays.sort(arr);

        twoNumsSum = new int[twoNumsSize];
        initTwoNums();
    }

    static void initTwoNums() {
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                twoNumsSum[idx] = arr[i] + arr[j];
                idx++;
            }
        }

        Arrays.sort(twoNumsSum);
    }
}
