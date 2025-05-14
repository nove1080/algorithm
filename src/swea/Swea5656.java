package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Swea5656 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static int maxR, maxC;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static int[][] originalBoard;
    static boolean[][] vis;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            simulate(0, new ArrayList<>());
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = Integer.MAX_VALUE;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        maxR = Integer.parseInt(input[2]);
        maxC = Integer.parseInt(input[1]);

        originalBoard = new int[maxR][maxC];
        vis = new boolean[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                originalBoard[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static void simulate(int depth, List<Integer> cols) {
        if(depth == n) {
            vis = new boolean[maxR][maxC];
            int[][] board = deepCopy(originalBoard);
            for (Integer col : cols) {
                vis = new boolean[maxR][maxC];
                board = drop(col, board);
            }

            answer = Math.min(sum(board), answer);
            return;
        }

        for (int i = 0; i < maxC; i++) {
            cols.add(i);
            simulate(depth + 1, cols);
            cols.remove(depth);
        }
    }

    public static int[][] drop(int col, int[][] board) {
        //col 위치에서 최초로 부딪히는 지점 찾기
        Queue<Point> q = new LinkedList<>();
        for (int row = 0; row < maxR; row++) {
            if(board[row][col] != 0) {
                q.add(new Point(row, col, board[row][col]));
                break;
            }
        }

        //벽돌 부수기를 전파
        while (!q.isEmpty()) {
            Point cur = q.poll();
            board[cur.r][cur.c] = 0;

            for (int dir = 0; dir < 4; dir++) {
                for (int i = 1; i < cur.boundary; i++) {
                    int nr = cur.r + moveR[dir] * i;
                    int nc = cur.c + moveC[dir] * i;

                    if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) break;
                    if (board[nr][nc] == 0) continue;
                    if (vis[nr][nc]) continue;

                    q.add(new Point(nr, nc, board[nr][nc]));
                    vis[nr][nc] = true;
                }
            }
        }

        //벽돌 중력 적용하기
        for (int c = 0; c < maxC; c++) {
            Stack<Integer> stk = new Stack<>();
            for (int r = 0; r < maxR; r++) {
                if(board[r][c] == 0) continue;
                stk.add(board[r][c]);
                board[r][c] = 0;
            }

            int i = 0;
            while (!stk.isEmpty()) {
                board[maxR - i - 1][c] = stk.pop();
                i++;
            }
        }
        return board;
    }

    public static int[][] deepCopy(int[][] arr) {
        int[][] copied = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                copied[i][j] = arr[i][j];
            }
        }

        return copied;
    }

    private static int sum(int[][] board) {
        int sum = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 0) continue;
                sum++;
            }
        }
        return sum;
    }

    static class Point {
        int r, c;
        int boundary;

        public Point(int r, int c, int boundary) {
            this.r = r;
            this.c = c;
            this.boundary = boundary;
        }
    }
}

