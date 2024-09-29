//16:15
/**
 * Q. 어떤 벽을 부술까?
 */
package bfs.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2206 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] board;
    static boolean[][][] visited; //[][][1] 벽을 부수지 않고 방문, [][][0] 벽을 부수고 방문
    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        init();

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            System.out.printf("cur = (%d, %d)\n", cur.r, cur.c);
            if (cur.r == n - 1 && cur.c == m - 1) {
                System.out.println(cur.depth);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextR = cur.r + moveR[dir];
                int nextC = cur.c + moveC[dir];

                if (nextR < 0 || nextC < 0 || nextR > n - 1 || nextC > m - 1) continue;
                if (visited[nextR][nextC][cur.breakChance]) continue;
                if (board[nextR][nextC] == 1) {
                    if (cur.breakChance != 0) {
                        System.out.printf("부순다 (%d, %d)\n", nextR, nextC);
                        q.add(new Point(nextR, nextC, cur.depth + 1, 0));
                        visited[nextR][nextC][0] = true;
                    }
                } else { //board == 0
                    q.add(new Point(nextR, nextC, cur.depth + 1, cur.breakChance));
                    visited[nextR][nextC][cur.breakChance] = true;
                }
            }
        }

        System.out.println(-1);
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        board = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(inputs[j]);
            }
        }
    }

    static class Point {
        int r, c;
        int depth;
        int breakChance;

        public Point(int r, int c) {
            this(r, c, 1, 1);
        }

        public Point(int r, int c, int depth, int breakChance) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.breakChance = breakChance;
        }
    }
}
