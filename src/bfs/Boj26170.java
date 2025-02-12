package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj26170 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n = 5;

    static int[][] board;
    static boolean[][] vis;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static Point startP;

    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        init();

        vis[startP.r][startP.c] = true;
        backtracking(startP, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void backtracking(Point cur, int depth, int apple) {
        if (depth >= answer) return;
        if (apple == 3) {
            answer = depth;
//            logging(cur, depth);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nextApple = apple;
            int nr = cur.r + moveR[dir];
            int nc = cur.c + moveC[dir];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (vis[nr][nc] || board[nr][nc] == -1) continue;
            if (board[nr][nc] == 1) nextApple++;

            vis[nr][nc] = true;
            backtracking(new Point(nr, nc), depth + 1, nextApple);
//            backtracking(new Point(nr, nc, cur.s + nr + ", " + nc + " -> "), depth + 1, nextApple);
            vis[nr][nc] = false;
        }
    }

    private static void logging(Point cur, int depth) {
        System.out.println("RENEW! [depth = " + depth + "]");
        System.out.println(cur.s);
    }

    public static void init() throws Exception {
        board = new int[n][n];
        vis = new boolean[n][n];
        for (int i = 0; i < 5; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        String[] input = br.readLine().split(" ");
        startP = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
    }

    static class Point {
        int r, c;
        String s = "";

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, String s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

}
