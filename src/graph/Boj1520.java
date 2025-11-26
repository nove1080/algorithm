package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1520 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int[][] board;
    static int[][] dp;

    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0, board[0][0]);
        System.out.println(dp[0][0]);
    }

    static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new int[maxR][maxC];
        dp = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = -1;
            }
        }
    }

    static void dfs(int r, int c, int height) {
        dp[r][c] = 0;
        if (r == maxR - 1 && c == maxC - 1) {
            dp[r][c] = 1;
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nr = r + moveR[dir];
            int nc = c + moveC[dir];

            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
            if (board[nr][nc] >= height) continue;
            if (dp[nr][nc] == -1) {
                dfs(nr, nc, board[nr][nc]);
            }
            dp[r][c] += dp[nr][nc];
        }
    }

}