//수 고르기
package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2230 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[] arr;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(arr);
        searchWith2Pointer();
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
    }

    public static void searchWith2Pointer() {
        int en = 0;
        for (int st = 0; st < n; st++) {
            while(en < n && arr[en] - arr[st] < m) {
                en++;
            }
            if (en == n) break;

            answer = Math.min(arr[en] - arr[st], answer);
        }
    }
}
