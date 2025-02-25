package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj14442_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int maxBreak; //최대 격파 횟수

    static int[][] board;
    static boolean[][][] vis; //vis[i][j][k]: (j, k) 지점에 i번 벽을 부순상태로 방문했는지 표기

    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs(new Point(0, 0, maxBreak, 0)));
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);
        maxBreak = Integer.parseInt(input[2]);

        board = new int[maxR][maxC];
        vis = new boolean[maxBreak + 1][maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                board[i][j] = cArr[j] - '0';
            }
        }
    }

    public static int bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        vis[start.breakChance][start.r][start.c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (isOut(cur)) return cur.depth + 1;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] == 1) {
                    if (cur.breakChance <= 0) continue; //벽을 부수지 못하는 경우
                    if (vis[cur.breakChance - 1][nr][nc]) continue; //방문한 적이 있는 경우

                    //큐에 추가
                    q.add(new Point(nr, nc, cur.breakChance - 1, cur.depth + 1));
                    vis[cur.breakChance - 1][nr][nc] = true;
                } else {
                    if(vis[cur.breakChance][nr][nc]) continue; //방문한 적이 있는 경우

                    //큐에 추가
                    q.add(new Point(nr, nc, cur.breakChance, cur.depth + 1));
                    vis[cur.breakChance][nr][nc] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isOut(Point cur) {
        return cur.r == maxR - 1 && cur.c == maxC - 1;
    }

    static class Point {
        int r, c;
        int breakChance; //벽을 부술 수 있는 남은 기회
        int depth;

        public Point(int r, int c, int breakChance, int depth) {
            this.r = r;
            this.c = c;
            this.breakChance = breakChance;
            this.depth = depth;
        }
    }
}
