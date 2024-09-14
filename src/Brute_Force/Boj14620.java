//꽃길
package Brute_Force;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj14620 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] costs;
    static int[][] board;
    static int[] moveR = new int[]{0,1,0,-1};
    static int[] moveC = new int[]{1,0,-1,0};
    static int n;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0);
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        costs = new int[n][n];
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static void dfs(int depth, int cost) {
        if(depth == 3) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if(board[r][c] == 0) {
                    boolean isSandable = true;
                    for (int dir = 0; dir < 4; dir++) {
                        int nr = r + moveR[dir];
                        int nc = c + moveC[dir];

                        if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                            isSandable = false;
                            break;
                        }
                        if (board[nr][nc] == 1) {
                            isSandable = false;
                            break;
                        }
                    }

                    if (isSandable) {
                        cost += costs[r][c];
                        board[r][c] = 1;
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = r + moveR[dir];
                            int nc = c + moveC[dir];

                            board[nr][nc] = 1;
                            cost += costs[nr][nc];
                        }
                        dfs(depth + 1, cost);
                        cost -= costs[r][c];
                        board[r][c] = 0;
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = r + moveR[dir];
                            int nc = c + moveC[dir];

                            board[nr][nc] = 0;
                            cost -= costs[nr][nc];
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
