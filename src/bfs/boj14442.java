//벽 부수고 이동하기 2
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj14442 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int maxWallBreaks;
    static boolean[][] board;
    static boolean[][][] vis;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs(0, 0));
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);
        maxWallBreaks = Integer.parseInt(input[2]);

        board = new boolean[maxR][maxC];
        vis = new boolean[maxWallBreaks + 1][maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]) == 0;
            }
        }
    }

    public static int bfs(int startR, int startC) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startR, startC, 0, 1));
        vis[0][startR][startC] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.r == maxR - 1 && cur.c == maxC - 1) return cur.depth;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (!board[nr][nc] && cur.breakCount < maxWallBreaks && !vis[cur.breakCount + 1][nr][nc]) {
                    q.add(new Point(nr, nc, cur.breakCount + 1, cur.depth + 1));
                    vis[cur.breakCount + 1][nr][nc] = true;
                }
                if (board[nr][nc] && !vis[cur.breakCount][nr][nc]) {
                    q.add(new Point(nr, nc, cur.breakCount, cur.depth + 1));
                    vis[cur.breakCount][nr][nc] = true;
                }
            }
        }

        return -1;
    }

    static class Point {
        int r, c;
        int breakCount;
        int depth;

        public Point(int r, int c, int breakCount, int depth) {
            this.r = r;
            this.c = c;
            this.breakCount = breakCount;
            this.depth = depth;
        }
    }
}
