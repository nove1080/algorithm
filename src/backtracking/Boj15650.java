// N과 M(2)
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15650 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static boolean[] vis;

    public static void main(String[] args) throws Exception {
        init();
        combination(-1, m);
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        vis = new boolean[n];
    }

    public static void combination(int start, int r) {
        if (r == 0) {
            for (int i = 0; i < n; i++) {
                if(vis[i]) {
                    sb.append(i + 1).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for (int next = start + 1; next < n; next++) {
            vis[next] = true;
            combination(next, r - 1);
            vis[next] = false;
        }
    }
}
