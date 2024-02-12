//경로 찾기
package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11403 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] adj;
    static boolean[] vis;
    static int[][] answer;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n; i++) {
            vis = new boolean[n];
            dfs(i, i);
        }
        for (int[] ints : answer) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        adj = new int[n][n];
        answer = new int[n][n];
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int origin, int v) {
        if (vis[v]) return;
        vis[v] = true;
        for (int next = 0; next < n; next++) {
            if (adj[v][next] == 0) continue;
            answer[origin][next] = 1;
            dfs(origin, next);
        }
    }
}
