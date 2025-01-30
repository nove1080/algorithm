//점수따먹기
package prefixsum;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11660 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[][] board;
    static int[][] sumBoard;
    static int[][] problems;

    public static void main(String[] args) throws Exception {
        init();
        for (int[] problem : problems) {
            sb.append(calculatePrefixSum(problem[0], problem[1], problem[2], problem[3])).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        board = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j < n + 1; j++) {
                board[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        problems = new int[m][4];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                problems[i][j] = Integer.parseInt(input[j]);
            }
        }

        initSumBoard();
    }

    public static void initSumBoard() {
        sumBoard = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sumBoard[i][j] = sumBoard[i - 1][j] + sumBoard[i][j - 1] - sumBoard[i - 1][j - 1] + board[i][j];
            }
        }
    }

    public static int calculatePrefixSum(int r1, int c1, int r2, int c2) {
        return sumBoard[r2][c2] - sumBoard[r2][c1 - 1] - sumBoard[r1 - 1][c2] + sumBoard[r1 - 1][c1 - 1];
    }

}
