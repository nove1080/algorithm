//ATM
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11399 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] times;
    static int answer;
    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(times);
        solve();
        System.out.println(answer);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int i = 0; i < n; i++) {
            answer += times[i] * (n - i);
        }
    }
}
