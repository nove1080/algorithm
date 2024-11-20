//합이 0인 네 정수
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj7453 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr01, arr02, arr03, arr04;
    static int[] sumArr01, sumArr02;
    static long answer;
    static int n;
    public static void main(String[] args) throws Exception {
        init();

        sumArr01 = createSumArr(arr01, arr02);
        sumArr02 = createSumArr(arr03, arr04);

        Arrays.sort(sumArr02);

        for (int num : sumArr01) {
            answer += getCount(sumArr02, num * (-1));
        }

        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        arr01 = new int[n];
        arr02 = new int[n];
        arr03 = new int[n];
        arr04 = new int[n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");

            arr01[i] = Integer.parseInt(input[0]);
            arr02[i] = Integer.parseInt(input[1]);
            arr03[i] = Integer.parseInt(input[2]);
            arr04[i] = Integer.parseInt(input[3]);
        }
    }

    public static int[] createSumArr(int[] arr01, int[] arr02) {
        int[] sumArr = new int[arr01.length * arr02.length];

        int idx = 0;
        for (int i = 0; i < arr01.length; i++) {
            for (int j = 0; j < arr02.length; j++) {
                sumArr[idx++] = arr01[i] + arr02[j];
            }
        }

        return sumArr;
    }

    public static int getCount(int[] arr, int target) {
        int lowerBound = lowerBound(arr, target);
        int upperBound = upperBound(arr, target);

        return upperBound - lowerBound;
    }

    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while(left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}
