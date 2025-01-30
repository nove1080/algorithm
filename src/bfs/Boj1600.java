//말이 되고픈 원숭이
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1600 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static boolean[][] board;
    static boolean[][][] vis; //vis[말로 이동한 횟수][R][C] = 이동 횟수

    static int maxHorseMoveCount;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static int[] moveHorseR = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] moveHorseC = {-1, 1, -2, 2, -2, 2, -1, 1};

    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs(0, 0));
    }

    public static void init() throws Exception {
        maxHorseMoveCount = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        maxC = Integer.parseInt(input[0]);
        maxR = Integer.parseInt(input[1]);

        board = new boolean[maxR][maxC];
        vis = new boolean[maxHorseMoveCount + 1][maxR][maxC];

        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]) == 0;
            }
        }
    }

    public static int bfs(int startR, int startC) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startR, startC, 0, 0));
        vis[0][startR][startC] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.r == maxR - 1 && cur.c == maxC - 1) return cur.depth;

            //원숭이
            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (!board[nr][nc] || vis[cur.horseCount][nr][nc]) continue;

                q.add(new Point(nr, nc, cur.depth + 1, cur.horseCount));
                vis[cur.horseCount][nr][nc] = true;
            }

            //말
            if (cur.horseCount < maxHorseMoveCount) {
                for (int dir = 0; dir < 8; dir++) {
                    int nr = cur.r + moveHorseR[dir];
                    int nc = cur.c + moveHorseC[dir];

                    if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                    if (!board[nr][nc] || vis[cur.horseCount + 1][nr][nc]) continue;

                    q.add(new Point(nr, nc, cur.depth + 1, cur.horseCount + 1));
                    vis[cur.horseCount + 1][nr][nc] = true;
                }
            }
        }

        return -1;
    }

    static class Point {
        int r, c;
        int depth;
        int horseCount;

        public Point(int r, int c, int depth, int horseCount) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.horseCount = horseCount;
        }

    }

}
