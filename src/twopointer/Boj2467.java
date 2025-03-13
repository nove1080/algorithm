package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2467 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;

    static int stNum, enNum;

    public static void main(String[] args) throws Exception {
        init();
        search();
        System.out.println(stNum + " " + enNum);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }

    public static void search() {
        int minimumDiff = Integer.MAX_VALUE;
        int st = 0;
        int en = n - 1;

        while (st < en) {
            int sum = Math.abs(arr[en] + arr[st]);

            if (minimumDiff >= sum) {
                minimumDiff = sum;
                stNum = arr[st];
                enNum = arr[en];
            }

            int absSt = Math.abs(arr[st]);
            int absEn = Math.abs(arr[en]);
            if (absSt < absEn) {
                en--;
            } else {
                st++;
            }
        }
    }
}
