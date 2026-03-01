package dfs;

import java.io.*;
import java.util.HashMap;

public class Boj17070 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int answer;

    static int[][] board;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int DIAGONAL = 2;
    static int[][] move;
    static HashMap<Integer, int[]> moveCommand = new HashMap<>();

    public static void main(String[] args) throws Exception {
        init();
        dfs(new Point(0, 1), HORIZONTAL);
        System.out.println(answer);

    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        //init moveCommand
        moveCommand.put(HORIZONTAL, new int[]{HORIZONTAL, DIAGONAL});
        moveCommand.put(VERTICAL, new int[]{VERTICAL, DIAGONAL});
        moveCommand.put(DIAGONAL, new int[]{HORIZONTAL, VERTICAL, DIAGONAL});

        //init move
        move = new int[3][2];
        move[HORIZONTAL] = new int[]{0, 1};
        move[VERTICAL] = new int[]{1, 0};
        move[DIAGONAL] = new int[]{1, 1};
    }

    public static void dfs(Point pos, int shape) {
        if (pos.isArrival()) {
            answer++;
            return;
        }

        for (int dir : moveCommand.get(shape)) {
            int nr = pos.r + move[dir][0];
            int nc = pos.c + move[dir][1];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (isInvalidPostion(dir, nr, nc)) continue;

            dfs(new Point(nr, nc), dir);
        }
    }

    private static boolean isInvalidPostion(int dir, int nr, int nc) {
        if (board[nr][nc] == 1) return true;

        if (dir == DIAGONAL) {
            return board[nr - 1][nc] == 1 || board[nr][nc - 1] == 1;
        }

        return false;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean isArrival() {
            return r == n - 1 && c == n - 1;
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

