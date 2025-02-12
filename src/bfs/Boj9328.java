//열쇠
package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj9328 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int testCase;

    static int maxR, maxC;
    static char[][] board;
    static int[][] vis; //value = 열쇠 조합 (if next.vis > vis[nr][nc]: 방문 했던 지점)

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static ArrayList<Point> starting;

    static int keys;
    static int docs;

    public static void main(String[] args) throws Exception {
        testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            init();
            bfsFromStartingPoints();
            sb.append(docs).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfsFromStartingPoints() {
        int beforeKey = keys;
        for (Point startPoint : starting) {
            if (vis[startPoint.r][startPoint.c] < keys) {
                bfs(startPoint);
            }
        }

        if (beforeKey != keys) { //열쇠의 변동이 생기면 다시 스타팅 포인트들을 재탐색할 기회를 준다.
            bfsFromStartingPoints();
        }
    }

    public static void bfs(Point p) {
        //벽 + 열쇠 없음
        if (board[p.r][p.c] >= 'A' && board[p.r][p.c] <= 'Z' && (keys & (1 << (board[p.r][p.c] - 'A'))) == 0) {
            return;
        }

        if (board[p.r][p.c] >= 'a' && board[p.r][p.c] <= 'z') { //키를 추가한다
            keys |= (1 << (board[p.r][p.c] - 'a'));
        }

        if (board[p.r][p.c] == '$') {
            board[p.r][p.c] = '.';
            docs++;
        }

        Queue<Point> q = new LinkedList<>();
        q.add(p);
        vis[p.r][p.c] = keys;

        while (!q.isEmpty()) {
            Point cur = q.poll();
//            System.out.println("CUR = " + cur);

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (board[nr][nc] == '*' || vis[nr][nc] >= keys) continue; //벽 + 해당 열쇠 조합으로 방문한 적 있음
                if (board[nr][nc] >= 'A' && board[nr][nc] <= 'Z' && (keys & (1 << (board[nr][nc] - 'A'))) == 0) continue; //문 + 열쇠 없음

                if (board[nr][nc] >= 'a' && board[nr][nc] <= 'z') { //키를 추가한다
                    keys |= (1 << (board[nr][nc] - 'a'));
//                    System.out.println("\tADD KEY = " + board[nr][nc]);
                }
                if (board[nr][nc] == '$') { 
                    board[nr][nc] = '.'; //$ 방문 횟수를 중복으로 더하는 것을 방지
                    docs++;
                }

                q.add(new Point(nr, nc));
                vis[nr][nc] = keys;
            }
        }
    }

    private static void init() throws IOException {
        docs = 0;
        keys = 0;
        starting = new ArrayList<>();

        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        vis = new int[maxR][maxC];

        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = input[j].charAt(0);
                vis[i][j] = -1;

                if (isPossibleStartingPoint(i, j)) { //시작지점이 될 수 있는 후보
                    starting.add(new Point(i, j));
                }
            }
        }

        input = br.readLine().split("");
        if (!input[0].equals("0")) {
            for (int i = 0; i < input.length; i++) {
                keys |= (1 << (input[i].charAt(0) - 'a'));
            }
        }
    }

    private static boolean isPossibleStartingPoint(int i, int j) {
        return (i == 0 || j == 0 || i == maxR - 1 || j == maxC - 1) && board[i][j] != '*';
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

}
