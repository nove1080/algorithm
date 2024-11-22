//수들의 합 2
package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2003 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }

    public static int solve() {
        int answer = 0;
        int en = 0;
        int sum = arr[0];

        for (int st = 0; st < n; st++) {
            while (en < n && sum < m) {
                en++;
                if (en != n) sum += arr[en];
            }
            if (sum == m) answer++;
            sum -= arr[st];
            if (en == n) break;
        }

        return answer;
    }
}
