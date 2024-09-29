//동전 0
/**
 * 입력: A[i]: A[i-1]의 배수
 */
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11047 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int size;
    static int goal;
    static int[] coins;
    public static void main(String[] args) throws Exception {
        init();
        solve();
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        coins = new int[size];
        for (int i = 0; i < size; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        int count = 0;
        for (int i = size - 1; i >= 0; i--) {
            while (goal - coins[i] >= 0) {
                count++;
                goal -= coins[i];
            }
        }
        System.out.println(count);
    }
}
