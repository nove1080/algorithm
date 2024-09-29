//4963: 미로 탐색
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4963 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] board;
    static boolean[][] vis;
    static int maxR, maxC;
    static int[] moveR = {1, 0, -1, 0, 1, -1, 1, -1};
    static int[] moveC = {0, 1, 0, -1, 1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        while (init()) {
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    static boolean init() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxC = Integer.parseInt(st.nextToken());
        maxR = Integer.parseInt(st.nextToken());

        if (maxC == 0 && maxR == 0) return false;

        board = new int[maxR][maxC];
        vis = new boolean[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return true;
    }

    static int bfs() {
        int result = 0;
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 1 && !vis[i][j]) {
                    result++;
                    q.add(new Point(i, j));
                    vis[i][j] = true;

                    while (!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int dir = 0; dir < 8; dir++) {
                            int nr = cur.r + moveR[dir];
                            int nc = cur.c + moveC[dir];

                            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                            if (board[nr][nc] != 1 || vis[nr][nc]) continue;

                            q.add(new Point(nr, nc));
                            vis[nr][nc] = true;
                        }
                    }
                }
            }
        }

        return result;
    }

    static class Point {
        public int r;
        public int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
