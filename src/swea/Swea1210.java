//Ladder1
package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Swea1210 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n = 100;
    static int[][] board;
    static boolean[][] vis;

    //하, 우, 좌
//    static int[] moveR = {1, 0, 0};
//    static int[] moveC = {0, 1, -1};

    static int[] moveR = {0, 0, 1};
    static int[] moveC = {1, -1, 0};

    static List<Point> starts; //시작점

    public static void main(String[] args) throws Exception {

        int tc = 10;
        for (int tt = 1; tt <= tc; tt++) {
            int t = Integer.parseInt(br.readLine());
            init();

            for (Point start : starts) {
                vis = new boolean[n][n];
                try{
                    dfs(start, start);
                } catch (RuntimeException e) {
                    break;
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        starts = new ArrayList<>();
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);

                if (i == 0 && board[i][j] == 1) { //시작점 추출
                    starts.add(new Point(i, j));
                }
            }
        }
    }

    //재귀를 이용한 DFS
    //출구로 나온 경우 수행중인 DFS 메서드들을 종료할 방법을 모르겠어서 포기
    public static void dfs(Point start, Point cur) {
        if(isOut(cur)) { //출구로 나온 경우
            if(board[cur.r][cur.c] == 2) {
                answer = start.c;
                throw new RuntimeException();
            }
            return;
        }
        System.out.println("CUR" + cur);
        for (int dir = 0; dir < 3; dir++) {
            int nr = cur.r + moveR[dir];
            int nc = cur.c + moveC[dir];

            if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if(board[nr][nc] == 0 || vis[nr][nc]) continue;

            vis[nr][nc] = true;
            dfs(start, new Point(cur.r, cur.c));
        }
    }

//    public static int dfs(Point start) {
//        Stack<Point> stk = new Stack<>();
//        stk.add(start);
//        vis[start.r][start.c] = true;
//
//        while (!stk.isEmpty()) {
//            Point cur = stk.pop();
//
//            if (isOut(cur)) {
//                if(board[cur.r][cur.c] == 2) {
//                    return start.c;
//                }
//                return -1;
//            }
//
//            for (int dir = 0; dir < 3; dir++) {
//                int nr = cur.r + moveR[dir];
//                int nc = cur.c + moveC[dir];
//
//                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
//                if (board[nr][nc] == 0 || vis[nr][nc]) continue;
//
//                stk.add(new Point(nr, nc));
//                vis[nr][nc] = true;
//            }
//        }
//
//        return -1;
//    }

    private static boolean isOut(Point cur) {
        return cur.r == n - 1;
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
