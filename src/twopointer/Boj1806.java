//부분합
package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1806 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int s;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(subtotal(s));
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }

    public static int subtotal(int total) {
        int minLength = Integer.MAX_VALUE;

        int en = 0;
        int sum = arr[0];
        for (int st = 0; st < n; st++) {
            while (en < n && sum < total) {
                en++;
                if (en != n) sum += arr[en];
            }
            if (en == n) break;
            minLength = Math.min(minLength, en - st + 1);
            sum -= arr[st];
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
