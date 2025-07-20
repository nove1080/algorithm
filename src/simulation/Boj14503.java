package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj14503 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static Machine m;
    static int[][] board;
    static boolean[][] vis;
    static int[] moveR = {-1, 0, 1, 0};
    static int[] moveC = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        System.out.println(getScore());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new int[maxR][maxC];
        vis = new boolean[maxR][maxC];

        input = br.readLine().split(" ");
        int curR = Integer.parseInt(input[0]);
        int curC = Integer.parseInt(input[1]);
        int dir = Integer.parseInt(input[2]);

        m = new Machine(curR, curC, dir);

        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    static void simulate() {
        while (true) {
            if (board[m.r][m.c] == 0 && !vis[m.r][m.c]) { //청소
                vis[m.r][m.c] = true;
            }
            if (hasUncleanArea(m)) {
                for (int i = 0; i < 4; i++) {
                    m.turnLeft();
                    if (canClean(m.returnFront())) {
                        m.goFront();
                        break;
                    }
                }
            } else {
                if (!m.goBack()) {
                    break;
                }
            }
        }
    }

    private static int getScore() {
        int score = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (vis[i][j]) {
                    score++;
                }
            }
        }
        return score;
    }

    private static boolean hasUncleanArea(Machine m) {
        for (int dir = 0; dir < 4; dir++) {
            int nr = m.r + moveR[dir];
            int nc = m.c + moveC[dir];

            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) {
                continue;
            }
            if (board[nr][nc] == 1 || vis[nr][nc]) {
                continue;
            }

            return true;
        }

        return false;
    }

    private static boolean canClean(Point p) {
        if (p.r < 0 || p.c < 0 || p.r >= maxR || p.c >= maxC) {
            return false;
        }
        if (board[p.r][p.c] == 1 || vis[p.r][p.c]) {
            return false;
        }

        return true;
    }

    static class Machine {

        int r, c;
        int dir;

        public Machine(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public int turnLeft() {
            dir = (dir + 3) % 4;
            return dir;
        }

        public Point returnFront() {
            int nr = r + moveR[dir];
            int nc = c + moveC[dir];

            return new Point(nr, nc);
        }

        public void goFront() {
            r = r + moveR[dir];
            c = c + moveC[dir];
        }

        public boolean goBack() {
            r = r + moveR[(dir + 2) % 4];
            c = c + moveC[(dir + 2) % 4];

            if (r < 0 || c < 0 || r >= maxR || c >= maxC) {
                return false;
            }
            if (board[r][c] == 1) {
                return false;
            }

            return true;
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
