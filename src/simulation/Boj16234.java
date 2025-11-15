package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj16234 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int day;
    static int n;
    static int minValue, maxValue;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        init();
        while(simulate()) {
            visited = new boolean[n][n];
            day++;
        }
        System.out.println(day);
    }

    private static boolean simulate() {
        boolean isChanged = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (merge(i, j)) isChanged = true;
                }
            }
        }

        return isChanged;
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        minValue = Integer.parseInt(input[1]);
        maxValue = Integer.parseInt(input[2]);

        visited = new boolean[n][n];
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static boolean merge(int startR, int startC) {
        int sum = board[startR][startC];
        List<Point> connectedList = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();

        Point curPoint = new Point(startR, startC);
        q.add(curPoint);
        connectedList.add(curPoint);
        visited[startR][startC] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (dontNeedConnect(cur.r, cur.c, nr, nc)) continue;

                Point nextPoint = new Point(nr, nc);
                q.add(nextPoint);
                connectedList.add(nextPoint);
                sum += board[nr][nc];
                visited[nr][nc] = true;
            }
        }

        doMerge(connectedList, sum);
        return connectedList.size() > 1;
    }

    private static boolean isOutOfRange(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= n || nc >= n;
    }

    private static boolean dontNeedConnect(int r, int c, int nr, int nc) {
        int pos1 = board[nr][nc];
        int pos2 = board[r][c];
        int diff = Math.abs(pos1 - pos2);

        return !(diff <= maxValue && diff >= minValue);
    }

    public static void doMerge(List<Point> connectedList, int sum) {
        int average = sum / connectedList.size();
        for (Point cur : connectedList) {
            board[cur.r][cur.c] = average;
        }
    }

    static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
