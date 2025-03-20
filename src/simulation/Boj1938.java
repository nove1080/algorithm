package simulation;

import java.io.*;
import java.util.*;

public class Boj1938 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static char[][] board;
    static boolean[][][] vis;

    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    //가로로 1,2,3 모양 기준
    static int[] rotateR = {-1, 1, 1, -1};
    static int[] rotateC = {1, 1, -1, -1};

    static Point[] start = new Point[3];
    static Train train;

    public static void main(String[] args) throws Exception {
        init();
//        System.out.println("BEFORE = " + train);
//        Train after = train.move(4);
//        System.out.println("AFTER = " + after);
        System.out.println(bfs());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(vis[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        board = new char[n][n];
        vis = new boolean[5][n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j].charAt(0);
                if (board[i][j] == 'B') {
                    start[idx++] = new Point(i, j);
                }
            }
        }

        //초기 모양 판단
        if (start[0].c + 1 == start[1].c) {
            train = new Train(start, 0, 0);
        } else {
            train = new Train(start, 1, 0);
        }
    }

    public static int bfs() {
        Queue<Train> q = new LinkedList<>();
        q.add(train);
        for (int i = 0; i < 3; i++) {
            vis[0][train.pos[i].r][train.pos[i].c] = true;
            vis[1][train.pos[i].r][train.pos[i].c] = true;
            vis[2][train.pos[i].r][train.pos[i].c] = true;
            vis[3][train.pos[i].r][train.pos[i].c] = true;
            vis[4][train.pos[i].r][train.pos[i].c] = true;
        }

        while (!q.isEmpty()) {
            Train cur = q.poll();
            System.out.println("CUR = " + cur);

            if (cur.isOut()) return cur.depth;


            for (int dir = 0; dir < 5; dir++) {
                Train next = cur.move(dir);
                if (next != null) {
                    q.add(next);
                }
            }
        }

        return 0;
    }

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }
    }

    static class Train {
        Point[] pos;
        int size = 3;
        int rotateCount;
        int depth;

        Train(Point[] p, int shape, int depth) {
            pos = p;
            this.rotateCount = shape;
            this.depth = depth;
        }

        /**
         * 기차를 움직인다.
         *
         * @param dir 이동 방향
         * @return 탈출 여부
         */
        public Train move(int dir) {
            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }
            System.out.println("MOVE " + dir);
            Point[] nextPos = new Point[3];
            if (dir != 4) { //상하좌우
                for (int i = 0; i < 3; i++) {
                    int nr = pos[i].r + moveR[dir];
                    int nc = pos[i].c + moveC[dir];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) return null;
                    if (board[nr][nc] == '1') return null;

                    nextPos[i] = new Point(nr, nc);
                }
            }

            if (dir == 4) { //회전
                System.out.println("ROTATE");
                for (int i = 0; i < 3; i++) {
                    if (i == 1) {
                        nextPos[i] = pos[i];
                        continue;
                    }

                    int nr = pos[i].r + rotateR[(rotateCount + i) % 4];
                    int nc = pos[i].c + rotateC[(rotateCount + i) % 4];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) return null;
                    if (board[nr][nc] == '1') return null;

                    nextPos[i] = new Point(nr, nc);
                }
            }

            //중복 방문 여부 체크
            int count = 0;
            for (int i = 0; i < 3; i++) {
                int r = nextPos[i].r;
                int c = nextPos[i].c;

                if (vis[dir][r][c]) count++;
            }

            //재방문
            if (count >= 3) return null;

            //방문 표시
            for (int i = 0; i < 3; i++) {
                int r = nextPos[i].r;
                int c = nextPos[i].c;

                vis[dir][r][c] = true;
            }
            //이동하기

            if (dir != 4) {
                return new Train(nextPos, rotateCount, depth + 1);
            }
            System.out.println("AFTER MOVE = " + new Train(nextPos, (rotateCount + 1) % 4, depth + 1));
            return new Train(nextPos, (rotateCount + 1) % 4, depth + 1);
        }

        public boolean isOut() {
            for (int i = 0; i < 3; i++) {
                if (board[pos[i].r][pos[i].c] != 'E') return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Train [pos=" + Arrays.toString(pos) + ", size=" + size + ", rotateCount=" + rotateCount + ", depth="
                    + depth + "]";
        }


    }
}
