//링크와 스타트 (15:54)
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15661 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] board;
    static boolean[] vis;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n / 2; i++) {
            backtracking(0, i + 1, 0);
        }

        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    /**
     *
     * @param depth
     * @param maxDepth
     * @param start : 시간복잡도를 낮추기 위함(없애니까 시간초과 발생)
     */
    public static void backtracking(int depth, int maxDepth, int start) {
        if (depth == maxDepth) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (vis[i] && vis[j]) diff += board[i][j];
                    if (!vis[i] && !vis[j]) diff -= board[i][j];
                }
            }
            answer = Math.min(answer, Math.abs(diff));
            return;
        }

        //팀원을 하나 추가한다
        for (int i = start; i < n; i++) {
            if (vis[i]) continue;

            vis[i] = true;
            backtracking(depth + 1, maxDepth, i);
            vis[i] = false;
        }
    }

}
