package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj14947 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MAX_SHAPE = 2;
    static final int ROW_LIE = 0; // 가로로 긴 방향
    static final int COL_LIE = 1; // 세로로 긴 방향
    static final int STAND = 2;
    static int maxR, maxC;

    static int[][] board;

    //하, 우, 상, 좌
    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    static boolean[][][] vis; //vis[i][j][k]: i 모양으로 (j,k) 위치에 방문한적이 있는지

    static Point start;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new int[maxR][maxC];
        vis = new boolean[MAX_SHAPE][maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                board[i][j] = cArr[j] - '0';
                if (board[i][j] == 2) {
                    start = new Point(i, j, 0);
                }
            }
        }
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        vis[STAND][start.c][start.c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.isStand()) { //서있는 경우
                for (int dir = 0; dir < 4; dir++) {
                    int prev = -1;
                    int nr = cur.r;
                    int nc = cur.c;
                    boolean isFail = false;

                    int shape = dir % 2 == 0 ? COL_LIE : ROW_LIE;

                    for (int boxSize = 0; boxSize < 3; boxSize++) {
                        nr = nr + moveR[dir];
                        nc = nc + moveC[dir];

                        if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) { // 2차원 배열 바깥
                            isFail = true;
                            break;
                        }
                        if (vis[shape][nr][nc]) { // 이전에 방문한 경우
                            isFail = true;
                            break;
                        }
                        if (board[nr][nc] == 0 && prev == 0) { // 연속한 싱크홀인 경우
                            isFail = true;
                            break;
                        }
                        prev = board[nr][nc];
                    }
                    if (isFail) continue;
                    q.add(new Point(nr, nc, cur.depth + 1));

                    for (int boxSize = 0; boxSize < 3; boxSize++) { // 방문 표시하기
                        if (board[nr][nc] == 3) return cur.depth + 1; // 도착했다면

                        vis[shape][nr][nc] = true;
                        nr = nr - moveR[dir];
                        nc = nc - moveC[dir];
                    }
                }
            } else if (cur.isColLie()) { //세로로 누워있는 경우

                for (int dir = 0; dir < 4; dir++) {
                    if (dir % 2 == 0) { //STAND 모양
                        int nextShape = STAND;

                        int nr = cur.r + moveR[dir];
                        int nc = cur.c + moveC[dir];

                        if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                        if (board[nr][nc] == 0) continue;
                        if (vis[nextShape][nr][nc]) continue;

                        q.add(new Point(nr, nc, cur.depth + 1));
                        vis[nextShape][nr][nc] = true;
                    } else { //ROW_LIE 모양

                    }
                }
            } else { //가로로 누워있는 경우

            }

        }

        return -1;
    }

    static class Point {
        int r, c;
        int depth;

        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

        public boolean isStand() {
            return depth % 3 == STAND;
        }

        public boolean isRowLie() {
            return depth % 3 == ROW_LIE;
        }

        public boolean isColLie() {
            return depth % 3 == COL_LIE;
        }
    }
}
