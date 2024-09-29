//링크와 스타트 (15:54)
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

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

    public static void backtracking(int depth, int maxDepth, int start) {
        if (depth == maxDepth) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (vis[i] && vis[j]) diff += board[i][j];
                    if (!vis[i] && !vis[j]) diff -= board[i][j];
                }
            }
            //4. 최소값이면 저장
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
