//말이 되고픈 원숭이
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1600 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int horseMoveCount;
    static int maxR, maxC;
    static int[][] board;
    static boolean[][][] visited; //[][][k] : 말로 k번째 방문한 경우

    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};
    static int[] horseMoveR = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horseMoveC = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, 0));
        for (int i = 0; i < horseMoveCount; i++) {
            visited[0][0][i] = true;
        }

        Point temp = null;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            temp = cur;
            if (cur.r == maxR - 1 && cur.c == maxC - 1) {
                System.out.println(cur.depth);
                return;
            }
            for (int dir = 0; dir < 4; dir++) { //원숭이 움직임
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] != 0 || visited[nr][nc][0]) continue;

                q.add(new Point(nr, nc, cur.depth + 1, cur.horseMoveCount));
                visited[nr][nc][0] = true;
            }

            if (cur.horseMoveCount < horseMoveCount) { //말로 이동할 수 있는 경우
                System.out.println("말 가능");
                for (int dir = 0; dir < 8; dir++) {
                    int nr = cur.r + horseMoveR[dir];
                    int nc = cur.c + horseMoveC[dir];

                    if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                    if (board[nr][nc] != 0 || visited[nr][nc][cur.horseMoveCount + 1]) continue;

                    q.add(new Point(nr, nc, cur.depth + 1, cur.horseMoveCount + 1));
                    visited[nr][nc][cur.horseMoveCount + 1] = true;
                }
            }
        }

        printVisited();
        System.out.println("fuck: " + temp);
        System.out.println(-1);
    }

    private static void printVisited() {
        for (int k = 0; k <=horseMoveCount; k++) {
            System.out.println("k=" + k);
            for (int i = 0; i < maxR; i++) {
                for (int j = 0; j < maxC; j++) {
                    int vis = visited[i][j][k] == true ? 1 : 0;
                    System.out.print(vis + " ");
                }
                System.out.println();
            }
            System.out.println("===================");
        }
    }

    public static void init() throws Exception {
        horseMoveCount = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        maxR = Integer.parseInt(inputs[1]);
        maxC = Integer.parseInt(inputs[0]);

        board = new int[maxR][maxC];
        visited = new boolean[maxR][maxC][horseMoveCount + 1];
        for (int i = 0; i < maxR; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(inputs[j]);
            }
        }
    }

    static class Point {
        int r, c, depth, horseMoveCount;

        public Point(int r, int c, int depth, int horseMoveCount) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.horseMoveCount = horseMoveCount;
        }

        @Override
        public String toString() {
            return "Point{" +
                "r=" + r +
                ", c=" + c +
                ", depth=" + depth +
                ", horseMoveCount=" + horseMoveCount +
                '}';
        }
    }

}

/**
1
7 8
0 0 0 0 0 0 0
1 1 1 1 1 1 0
1 1 1 1 1 1 0
0 0 0 1 1 1 0
0 1 1 1 0 0 0
0 1 1 1 1 1 1
0 1 1 1 1 1 1
0 0 0 0 0 0 0
 *
 *
 *
 */
