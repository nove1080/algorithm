package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea2115 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n, m, c;
    static int[][] board;

    static int sumA, sumB;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    comb(i, j, -1, -1, 1);
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void comb(int r1, int c1, int r2, int c2, int depth) {
        if(depth == 0) {
//            System.out.println(r1 + ", " + c1 + ", " + r2 + ", " + c2);
            sumA = 0;
            for (int i = 1; i <= m; i++) {
                comb(r1, c1 - 1, c1 + m, i, 0, 0, true);
            }
//            System.out.println("\tsumA = " + sumA);
            sumB = 0;
            for (int i = 1; i <= m; i++) {
                comb(r2, c2 - 1, c2 + m, i, 0, 0, false);
            }
//            System.out.println("\tsumB = " + sumB);
//            System.out.println("\t\tsumA + sumB = " + (sumA + sumB));
            answer = Math.max(answer, sumA + sumB);
            return;
        }

        for (int c = c1 + m; c <= n - m; c++) {
            comb(r1, c1, r1, c, 0);
        }

        for (int i = r1 + 1; i < n; i++) {
            for (int j = 0; j <= n - m; j++) {
                comb(r1, c1, i, j, 0);
            }
        }
    }

    public static void comb(int r, int curC, int maxC, int depth, int sum, int revenue, boolean subsetA) {

        if (depth == 0) {
            if(sum <= c) {
                if(subsetA) sumA = Math.max(sumA, revenue);
                else sumB = Math.max(sumB, revenue);
            }
            return;
        }

        for (int nextC = curC + 1; nextC < maxC; nextC++) {
            comb(r, nextC, maxC, depth - 1, sum + board[r][nextC], revenue + board[r][nextC] * board[r][nextC], subsetA);
        }
    }

    public static void init() throws Exception {
        answer = 0;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

}
