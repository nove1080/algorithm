//아기 상어
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16236 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] board;
    static boolean[][] vis;
    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static Shark shark;
    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        while (bfs() != 0) {
            vis = new boolean[n][n];
        }

        System.out.println(answer);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    shark = new Shark(new Point(i, j));
                    board[i][j] = 0;
                }
            }
        }
    }

    //물고기 한마리 먹기
    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> feeds = new PriorityQueue<>((p1, p2) -> {
            if (p1.time == p2.time) {
                if (p1.r != p2.r) {
                    return p1.r - p2.r;
                } else {
                    return p1.c - p2.c;
                }
            } else {
                return p1.time - p2.time;
            }
        });

        q.add(shark.pos);
        vis[shark.pos.r][shark.pos.c] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = moveR[i] + cur.r;
                int nc = moveC[i] + cur.c;

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                    continue;
                }
                if (vis[nr][nc] || board[nr][nc] > shark.weight) {
                    continue;
                }

                //먹이를 찾음
                if (board[nr][nc] != 0 && board[nr][nc] < shark.weight) {
                    feeds.add(new Point(nr, nc, cur.time + 1));
                }

                //지나감
                else if (board[nr][nc] == 0 || board[nr][nc] == shark.weight) {
                    q.add(new Point(nr, nc, cur.time + 1));
                    vis[nr][nc] = true;
                }
            }
        }

        //가장 높은 우선순위를 갖는 먹이를 먹는다.
        if (!feeds.isEmpty()) {
            Point feed = feeds.poll();
            System.out.println("===========[eat]===========");
            System.out.println("feed.weight: " + board[feed.r][feed.c]);
            System.out.println("eat: (" + feed.r + "," + feed.c + ")");
            System.out.println("time: " + feed.time + "s");
            shark.eat(feed.r, feed.c);
            board[feed.r][feed.c] = 0;
            answer += feed.time;
            return feed.time;
        }
        return 0;
    }

    static class Shark {

        int weight = 2;
        int cnt;
        Point pos;

        public Shark(Point p) {
            this.pos = p;
        }

        public void eat(int r, int c) {
            if (weight == cnt + 1) {
                weight++;
                cnt = 0;
            } else {
                cnt++;
            }

            this.pos = new Point(r, c);
        }
    }

    static class Point {

        int r;
        int c;
        int time;

        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
