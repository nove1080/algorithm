package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2096 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;

    static int[][] board;
    static int[][] maxBoard;
    static int[][] minBoard;

    public static void main(String[] args) throws Exception {
        init();

        /**
         * d[i][k] = i번째 줄의 k번째 위치의 최대 합
         * d[k][0] = arr[k] vs d[i]
         *          if arr[k] >
         */

        //최대
        for (int i = 1; i < n; i++) {
            maxBoard[i][0] = Math.max(maxBoard[i - 1][0], maxBoard[i - 1][1]) + board[i][0];
            maxBoard[i][1] = Math.max(maxBoard[i - 1][0], Math.max(maxBoard[i - 1][1], maxBoard[i - 1][2])) + board[i][1];
            maxBoard[i][2] = Math.max(maxBoard[i - 1][1], maxBoard[i - 1][2]) + board[i][2];
        }

        sb.append(Math.max(maxBoard[n - 1][0], Math.max(maxBoard[n - 1][1], maxBoard[n - 1][2]))).append(" ");

        //최소
        for (int i = 1; i < n; i++) {
            minBoard[i][0] = Math.min(minBoard[i - 1][0], minBoard[i - 1][1]) + board[i][0];
            minBoard[i][1] = Math.min(minBoard[i - 1][0], Math.min(minBoard[i - 1][1], minBoard[i - 1][2])) + board[i][1];
            minBoard[i][2] = Math.min(minBoard[i - 1][1], minBoard[i - 1][2]) + board[i][2];
        }

        sb.append(Math.min(minBoard[n - 1][0], Math.min(minBoard[n - 1][1], minBoard[n - 1][2])));

        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        board = new int[n][3];
        maxBoard = new int[n][3];
        minBoard = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        maxBoard[0][0] = board[0][0];
        maxBoard[0][1] = board[0][1];
        maxBoard[0][2] = board[0][2];

        minBoard[0][0] = board[0][0];
        minBoard[0][1] = board[0][1];
        minBoard[0][2] = board[0][2];
    }

}
