package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Swea1861 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int room;
    static int maxVisitableRoom;

    static int n;
    static int[][] board;

    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfs(new Point(i, j));
                }
            }
            sb.append("#").append(t).append(" ").append(room).append(" ").append(maxVisitableRoom).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        room = Integer.MAX_VALUE;
        maxVisitableRoom = Integer.MIN_VALUE;

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static void bfs(Point start) {
        boolean[][] vis = new boolean[n][n];

        Queue<Point> q = new LinkedList<>();
        q.add(start);
        vis[start.r][start.c] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            cnt++;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (vis[nr][nc]) continue;
                if (board[nr][nc] != board[cur.r][cur.c] + 1) continue;

                q.add(new Point(nr, nc));
                vis[nr][nc] = true;
            }
        }


        if (cnt == maxVisitableRoom) {
            room = Math.min(room, board[start.r][start.c]);
            maxVisitableRoom = cnt;
        }
        if (cnt > maxVisitableRoom) {
            room = board[start.r][start.c];
            maxVisitableRoom = cnt;
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
