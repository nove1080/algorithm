package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2665 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    static int[][] vis;
    static int[][] board;

    static int[] moveR = {0, 1, 0, -1};
    static int[] moveC = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs(0, 0));
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        vis = new int[n][n];
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            Arrays.fill(vis[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j] - '0';
            }
        }
    }

    static int bfs(int startR, int startC) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startR, startC, 0));
        vis[startR][startC] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                int nextCrush = cur.crush;
                if (board[nr][nc] == 1) { //흰 방
                    if (vis[nr][nc] <= nextCrush) continue;
                } else { //검은 방
                    nextCrush++;
                    if (vis[nr][nc] <= nextCrush) continue;
                }

                q.add(new Point(nr, nc, nextCrush));
                vis[nr][nc] = nextCrush;
            }
        }

        return vis[n - 1][n - 1];
    }

    static void print(int[][] board) {
        System.out.println("=======");
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Point {
        int r, c;
        int crush;

        public Point(int r, int c, int crush) {
            this.r = r;
            this.c = c;
            this.crush = crush;
        }
    }
}
