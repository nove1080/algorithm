package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea4012 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static int[][] board;

    static boolean[] vis;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            for (int i = 0; i <= n / 2; i++) {
                combination(-1, i);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    /**
     * 가능한 재료의 모든 조합을 만들어내고 맛의 차이를 갱신
     * @param start 선택 가능한 재료들의 시작 인덱스
     * @param r 선택할 재료의 수
     */
    public static void combination(int start, int r) {
        if (r == 0) {
            answer = Math.min(answer, getDiff());
        }

        for (int next = start + 1; next < n; next++) {
            vis[next] = true;
            combination(next, r - 1);
            vis[next] = false;
        }
    }

    /**
     * 맛의 차이를 반환
     * @return 맛의 차이
     */
    public static int getDiff() {
        int diff = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i] == vis[j]) {
                    if (vis[i])  diff += board[i][j];
                    if (!vis[i]) diff -= board[i][j];
                }
            }
        }

        return Math.abs(diff);
    }
}
