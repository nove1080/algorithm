//알고스팟
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj1261 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static boolean[][][] vis; //[][][벽 부순 횟수]
    static boolean[][] pqVis; //priorityQueue를 사용할 때 vis
    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    static int maxR, maxC;
    static int maxWall;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfsWithPriorityQueue(0, 0));
//        System.out.println(bfs());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxC = Integer.parseInt(input[0]);
        maxR = Integer.parseInt(input[1]);
        
        maxWall = maxR * maxC;

        board = new int[maxR][maxC];
        vis = new boolean[maxR][maxC][maxWall];
        pqVis = new boolean[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static int bfs() {
        int answer = Integer.MAX_VALUE;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0));
        for (int i = 0; i < maxWall; i++) {
            vis[0][0][i] = true;
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (cur.r == maxR - 1 && cur.c == maxC - 1) {
                answer = Math.min(cur.cnt, answer);
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = moveR[dir] + cur.r;
                int nc = moveC[dir] + cur.c;

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (answer <= cur.cnt) continue; //볼 필요도 없음

                if (board[nr][nc] == 0) {
                    if(vis[nr][nc][cur.cnt]) continue;

                    q.add(new Point(nr, nc, cur.cnt));
                    vis[nr][nc][cur.cnt] = true;
                } else { //벽인 경우
                    if(vis[nr][nc][cur.cnt + 1]) continue;

                    q.add(new Point(nr, nc, cur.cnt + 1));
                    vis[nr][nc][cur.cnt + 1] = true;
                }
            }
        }

        return answer;
    }

    /*
     * 가장 벽을 적게 부순 Point 순으로 BFS를 진행한다.
     * 최초로 (maxR, maxC) 지점에 도달하면 가장 벽을 적게 부수며 도달한 것
     */
    public static int bfsWithPriorityQueue(int r, int c) {
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            return p1.cnt - p2.cnt;
        });
        pq.add(new Point(r, c, 0));
        pqVis[r][c] = true;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.r == maxR - 1 && cur.c == maxC - 1) {
                return cur.cnt;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = moveR[dir] + cur.r;
                int nc = moveC[dir] + cur.c;

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] == 0 && !pqVis[nr][nc]) {
                    pq.add(new Point(nr, nc, cur.cnt));
                    pqVis[nr][nc] = true;
                }
                if (board[nr][nc] == 1 && !pqVis[nr][nc]) {
                    pq.add(new Point(nr, nc, cur.cnt + 1));
                    pqVis[nr][nc] = true;
                }
            }
        }

        return -1;
    }

    static class Point {
        public int r, c, cnt; //cnt = 벽 부순 횟수

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}
