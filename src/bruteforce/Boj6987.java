package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj6987 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;

    static int[][] board;
    static boolean isPossible;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 4; i++) {
            init();
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = 6;
        isPossible = true;
        String[] input = br.readLine().split(" ");

        board = new int[n][3];
        for (int i = 0; i < n; i++) {
            board[i][0] = Integer.parseInt(input[i * 3]);
            board[i][1] = Integer.parseInt(input[i * 3 + 1]);
            board[i][2] = Integer.parseInt(input[i * 3 + 2]);
        }
    }

    public static void backtracking(int nation) {
        if(nation == n) {
            isPossible = false;
            return;
        }

        for (int next = 0; next < n; next++) {
            if(next == nation) continue;

        }
    }
}
