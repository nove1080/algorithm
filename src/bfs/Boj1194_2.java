//달이 차오른다, 가자.
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1194_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static char[][] board;
    static boolean[][][] vis; //가지고 있는 열쇠 조합에 따라 왔던 길을 돌아갈 수도 있음 [열쇠 조합][maxR][maxC]

    static int keyIndex;
    static HashMap<String, Integer> keyToIdxMapper = new LinkedHashMap<>();

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static Point startPoint;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bfs());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        vis = new boolean[(int) Math.pow(2, 6)][maxR][maxC]; //[key 조합 수][maxR][maxC]
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = input[j].charAt(0);
                if (board[i][j] == '0') {
                    startPoint = new Point(i, j, 0, 0);
                }
            }
        }
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(startPoint);
        vis[0][startPoint.r][startPoint.c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (board[cur.r][cur.c] == '1') return cur.depth;

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;

                char mark = board[nr][nc];
                if (mark == '#') continue;

                Point next = new Point(nr, nc, cur.depth + 1, cur.keys);

                if (vis[next.getMyKeyIdx()][nr][nc]) continue;
                if (mark >= 'a' && mark <= 'f') next.addKey(mark - 'a');
                if (mark >= 'A' && mark <= 'F' && !next.hasKey(Character.toLowerCase(mark) - 'a')) continue;

                q.add(next);
                vis[next.getMyKeyIdx()][nr][nc] = true;
            }
        }

        return -1;
    }

    static class Point {
        int r, c, depth;
        int keys;

        public Point(int r, int c, int depth, int keys) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.keys = keys;
        }

        public void addKey(int key) {
            keys |= 1 << key;
        }

        public boolean hasKey(int key) {
            return (keys & (1 << key)) != 0;
        }

        /**
         * 가지고 있는 열쇠 조합에 따른 고유 숫자를 반환(비트마스킹 사용)
         * @return 열쇠 조합에 따른 고유 숫자
         */
        public int getMyKeyIdx() {
            return keys;
        }
    }

}
