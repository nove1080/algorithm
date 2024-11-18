//숫자 카드
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10815 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] arr01;
    static int[] arr02;
    public static void main(String[] args) throws Exception {
        init();

        Arrays.sort(arr01);

        for (int i : arr02) {
            if(Arrays.binarySearch(arr01, i) >= 0) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr01 = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr01[i] = Integer.parseInt(input[i]);
        }

        m = Integer.parseInt(br.readLine());
        arr02 = new int[m];
        input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            arr02[i] = Integer.parseInt(input[i]);
        }
    }
}
