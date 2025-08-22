package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Boj6087 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static char[][] board;
    static int[][][] vis;

    /**
     * 상 하 좌 우
     */
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};
    static Point[] spots = new Point[2];

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra());
    }

    static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxC = Integer.parseInt(input[0]);
        maxR = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        vis = new int[maxR][maxC][4];

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                Arrays.fill(vis[i][j], Integer.MAX_VALUE);
            }
        }

        int idx = 0;
        for (int i = 0; i < maxR; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 'C') {
                    spots[idx++] = new Point(i, j, 0, -1);
                }
            }
        }
    }

    public static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.count - p2.count);
        Point start = spots[0];
        pq.add(start);

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            if (cur.r == spots[1].r && cur.c == spots[1].c) return cur.count;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] == '*') continue;

                int count = cur.count;
                if (cur.dir != -1 && cur.dir != dir) count++;
                if (vis[nr][nc][dir] <= count) continue;

                pq.add(new Point(nr, nc, count, dir));
                vis[nr][nc][dir] = count;
            }
        }

        return -1;
    }

    static class Point {
        int dir;
        int r, c;
        int count;

        public Point(int r, int c, int count, int dir) {
            this.c = c;
            this.count = count;
            this.r = r;
            this.dir = dir;
        }
    }
}
