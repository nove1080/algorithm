//연구소
package Bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int maxR, maxC;
    static int[][] board;
    static boolean[][] vis;
    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static int maxSafetyArea = 0;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 0) {
                    constructWall(i, j, 0);
                }
            }
        }
        System.out.println(maxSafetyArea);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        maxR = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());

        board = new int[maxR][maxC];
        vis = new boolean[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void constructWall(int r, int c, int order) {
        if (order == 2) {
            board[r][c] = 1;
            maxSafetyArea = Math.max(maxSafetyArea, bfs(board));
            vis = new boolean[maxR][maxC];
            board[r][c] = 0;
            return;
        }
        board[r][c] = 1;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] == 0) {
                    constructWall(i, j, order + 1);
                }
            }
        }
        board[r][c] = 0;
    }

    //return 안전 지역 너비
    static int bfs(int[][] board) {
        int[][] tempBoard = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            tempBoard[i] = board[i].clone();
        }

        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                //바이러스 확산
                if (tempBoard[i][j] == 2 && !vis[i][j]) {
                    q.add(new Point(i, j));
                    vis[i][j] = true;
                    while (!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nr = cur.r + moveR[dir];
                            int nc = cur.c + moveC[dir];

                            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                            if (tempBoard[nr][nc] == 1 || vis[nr][nc]) continue;

                            q.add(new Point(nr, nc));
                            vis[nr][nc] = true;
                            tempBoard[nr][nc] = 2;
                        }
                    }
                }
            }
        }

        int safetyArea = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (tempBoard[i][j] == 0) safetyArea++;
            }
        }

        return safetyArea;
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

