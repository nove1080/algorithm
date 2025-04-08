package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2239 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n = 9;

    static int[][] board;

    static boolean[][] rowUsed;
    static boolean[][] colUsed;
    static boolean[][] boxUsed;

    static String answer = "";

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0);
        System.out.println(answer);
    }

    public static void init() throws Exception {
        rowUsed = new boolean[n][n + 1];
        colUsed = new boolean[n][n + 1];
        boxUsed = new boolean[n][n + 1];

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = input[j] - '0';

                markUsed(i, board[i][j], true, j, getSection(i, j));
            }
        }
    }

    public static void dfs(int r, int c) {
        if (!answer.isBlank()) return;
        if (r > n - 1) {
            answer = getStr();
            return;
        }
        if (c >= n) {
            dfs(r + 1, 0);
            return;
        }
        if (board[r][c] != 0) {
            dfs(r, c + 1);
            return;
        }

        for (int i = 1; i < 10; i++) {
            int section = getSection(r, c);
            if (!rowUsed[r][i] && !colUsed[c][i] && !boxUsed[section][i]) { //넣을 수 있는 숫자
                board[r][c] = i;
                markUsed(r, i, true, c, section);

                dfs(r, c + 1);

                board[r][c] = 0;
                markUsed(r, i, false, c, section);
            }
        }
    }

    private static void markUsed(int r, int i, boolean mark, int c, int section) {
        rowUsed[r][i] = mark;
        colUsed[c][i] = mark;
        boxUsed[section][i] = mark;
    }

    private static String getStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static int getSection(int r, int c) {
        if (r <= 2 && c <= 2) return 0;
        else if (r <= 2 && c <= 5) return 1;
        else if (r <= 2 && c <= n - 1) return 2;
        else if (r <= 5 && c <= 2) return 3;
        else if (r <= 5 && c <= 5) return 4;
        else if (r <= 5 && c <= n - 1) return 5;
        else if (r <= n - 1 && c <= 2) return 6;
        else if (r <= n - 1 && c <= 5) return 7;
        else return 8;
    }
}
