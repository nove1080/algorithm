package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj11404 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;

    static int[][] board;

    static final int MAX_COST = 10_000_001;

    public static void main(String[] args) throws Exception {
        init();
        print(floyd());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                board[i][j] = MAX_COST;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to   = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            board[from][to] = Math.min(board[from][to], cost);
        }
    }

    public static int[][] floyd() {
        for (int i = 0; i < n; i++) { //i = 거쳐갈 노드
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(i == j) break;
                    board[j][k] = Math.min(board[j][k], board[j][i] + board[i][k]);
                }
            }
        }

        return board;
    }


    private static void print(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j] == MAX_COST ? 0 : board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
