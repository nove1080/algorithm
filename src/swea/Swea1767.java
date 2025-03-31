package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Swea1767 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int maximumCount;
    static int minimumLength;

    static int n;
    static int[][] board;

    static List<Point> nonEdgeCores;

    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static boolean[][] vis;


    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            backtracking(0, 0, 0);

            answer = minimumLength;
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;
        maximumCount = 0;
        minimumLength = Integer.MAX_VALUE;
        nonEdgeCores = new ArrayList<>();

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        vis = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if(board[i][j] == 1) {
                    if(i == 0 || j == 0 || i == n - 1 || j == n - 1) continue;
                    nonEdgeCores.add(new Point(i, j));
                }
            }
        }
    }

    public static void backtracking(int count, int length, int depth) {
        //남은 코어를 모두 연결시켜도 기존에 기록된 최고 코어 수를 넘지 못하는 경우
        if(nonEdgeCores.size() - depth + count < maximumCount) return;
        //모든 코어를 대상으로 연결을 시도한 경우
        if(depth == nonEdgeCores.size()) {
            //현재 최고 코어 이상을 연결하고도 길이가 더 짧은 경우
            if (count > maximumCount) {
                maximumCount = count;
                minimumLength = length;
            } else if (count == maximumCount && length < minimumLength) {
                minimumLength = length;
            }

            return;
        }

        boolean neverConnected = true;
        for (int dir = 0; dir < 4; dir++) {
            boolean isConnected = true;
            int cable = 0;

            int curR = nonEdgeCores.get(depth).r;
            int curC = nonEdgeCores.get(depth).c;

            while (true) {
                int nr = curR + moveR[dir];
                int nc = curC + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                    break;
                }

                if (vis[nr][nc] || board[nr][nc] == 1) {
                    isConnected = false;
                    break;
                }

                vis[nr][nc] = true; //연결 표시하기

                cable++;
                curR = nr;
                curC = nc;
            }

            //연결시키고 다음으로
            if (isConnected) {
                neverConnected = false;
                backtracking(count + 1, length + cable, depth + 1);
            }

            //연결 취소
            curR = nonEdgeCores.get(depth).r;
            curC = nonEdgeCores.get(depth).c;
            for (int i = 0; i < cable; i++) {
                int nr = curR + moveR[dir];
                int nc = curC + moveC[dir];

                vis[nr][nc] = false;

                curR = nr;
                curC = nc;
            }
        }

        //연결시키지 않고 다음으로
        backtracking(count, length, depth + 1);
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
