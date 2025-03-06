package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Swea1868 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static char[][] board;
    static boolean[][] vis;

    //8방향
    static int[] moveR = {0, 1, 0, -1, 1, 1, -1, -1};
    static int[] moveC = {1, 0, -1, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            searchZeroTile();
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == '0' && !vis[i][j]) {
                        count++;
                        clickZeroTile(new Point(i, j));
                    }
                }
            }
            answer += getRemainTileNumber() + count;
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;
        n = Integer.parseInt(br.readLine());

        vis = new boolean[n][n];
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j].charAt(0);
                if (board[i][j] == '.') board[i][j] = '0';
                if (board[i][j] == '*') vis[i][j] = true;
            }
        }
    }

    public static void searchZeroTile() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == '*') {
                    for (int dir = 0; dir < 8; dir++) {
                        int nr = r + moveR[dir];
                        int nc = c + moveC[dir];

                        if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                        if (board[nr][nc] == '*') continue;
                        board[nr][nc]++;
                    }
                }
            }
        }
    }

    //0이 표기된 타일을 클릭한다.
    public static void clickZeroTile(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.add(p);
        vis[p.r][p.c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 8; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (vis[nr][nc]) continue;

                vis[nr][nc] = true;
                if (board[nr][nc] != '0') continue;
                q.add(new Point(nr, nc));
            }
        }
    }

    public static int getRemainTileNumber() {
        int remainTile = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j]) continue;
                remainTile++;
            }
        }

        return remainTile;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
