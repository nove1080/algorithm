package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj3055 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;

    static char[][] board;
    static int[][][] vis;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static Point dest;
    static Point start;
    static List<Point> waters;

    public static void main(String[] args) throws Exception {
        init();
        bfsWater();
        System.out.println(bfsHedgehog());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        waters = new ArrayList<>();
        for (int i = 0; i < maxR; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 'D') dest = new Point(i, j, 0);
                else if (board[i][j] == 'S') start = new Point(i, j, 0);
                else if (board[i][j] == '*') waters.add(new Point(i, j, 0));
            }
        }

        vis = new int[2][maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            Arrays.fill(vis[0][i], Integer.MAX_VALUE);
            Arrays.fill(vis[1][i], Integer.MAX_VALUE);
        }
    }

    public static void bfsWater() {
        Queue<Point> q = new LinkedList<>();
        for (Point water : waters) {
            q.add(water);
            vis[0][water.r][water.c] = 0;
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] != '.') continue;
                if (vis[0][nr][nc] <= cur.depth + 1) continue;


                q.add(new Point(nr, nc, cur.depth + 1));
                vis[0][nr][nc] = cur.depth + 1;
            }
        }
    }

    public static String bfsHedgehog() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        vis[1][start.r][start.c] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.isOut()) return Integer.toString(cur.depth);

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] == 'X') continue; // 바위는 못 지나감
                if (vis[0][nr][nc] <= cur.depth + 1) continue; //물이 전파된 경우
                if (vis[1][nr][nc] <= cur.depth + 1) continue; //내가 이전에 방문한 경우

                q.add(new Point(nr, nc, cur.depth + 1));
                vis[1][nr][nc] = cur.depth + 1;
            }
        }

        return "KAKTUS";
    }

    static class Point {
        int r, c;
        int depth;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

        public boolean isOut() {
            return this.r == dest.r && this.c == dest.c;
        }
    }
}
