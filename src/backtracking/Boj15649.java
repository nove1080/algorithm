// N과 M(1) [S3]
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15649 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static boolean[] vis;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        permutation(0);
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new int[m];
        vis = new boolean[n];
    }

    public static void permutation(int depth) {
        if (depth == m) {
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
			vis[i] = true;
            arr[depth] = i + 1;
            permutation(depth + 1);
            vis[i] = false;
        }
    }
}
