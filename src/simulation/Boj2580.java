package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//2580: 스도쿠
public class Boj2580 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] board;
    static int n = 9;
    static int missing;
    static Point[] missingPoints;

    public static void main(String[] args) throws Exception {
        init();
        fillSudoku(0);
    }

    public static void init() throws Exception {
        List<Point> missingPointList = new ArrayList<>();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == 0) {
                    missing++;
                    missingPointList.add(new Point(i, j));
                }
            }
        }

        missingPoints = missingPointList.toArray(new Point[0]);
    }

    public static void fillSudoku(int depth) {
        if (depth == missing) {
            writeCurrentBoard();
            System.out.println(sb);
            System.exit(0);
        }

        Point cur = missingPoints[depth];
        for (int i = 1; i < 10; i++) {
            if (isValid(cur, i)) {
                board[cur.r][cur.c] = i;
                fillSudoku(depth + 1);
                board[cur.r][cur.c] = 0;
            }
        }
    }

    public static boolean isValid(Point p, int num) {
        for (int i = 0; i < n; i++) {
            if (board[i][p.c] == num || board[p.r][i] == num) {
                return false;
            }
        }

        Point leftTopPoint = p.getMyAreaLeftPoint();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = leftTopPoint.r + i;
                int c = leftTopPoint.c + j;

                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void writeCurrentBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point getMyAreaLeftPoint() {
            return new Point((r / 3) * 3, (c / 3) * 3);
        }
    }
}

