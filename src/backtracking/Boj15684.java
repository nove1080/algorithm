package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15684 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int totalLines;

    static int[][] board; //board[depth][start] -> depth 번째에서 start와 연결된 선의 숫자

    static int answer;

    public static void main(String[] args) throws Exception {
        init();

        for (int j = 0; j < 4; j++) {
            backtracking(1, j, j);
        }

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < maxR + 1; i++) {
            System.out.println();
            for (int j = 0; j < maxC + 1; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[2]);
        maxC = Integer.parseInt(input[0]);
        totalLines = Integer.parseInt(input[1]);

        board = new int[maxR + 1][maxC + 1];
        for (int i = 0; i < totalLines; i++) {
            input = br.readLine().split(" ");

            int depth = Integer.parseInt(input[0]);
            int n1 = Integer.parseInt(input[1]);
            int n2 = n1 + 1;

            board[depth][n1] = n2;
            board[depth][n2] = n1;
        }

        answer = Integer.MAX_VALUE;
    }

    static void backtracking(int start, int n, int r) {
        if (r == 0) {
            for (int line = 1; line < maxC + 1; line++) {
                if (!simulate(line)) return;
            }

            answer = Math.min(answer, n);
            return;
        }

        for (int next = 0; next < maxC; next++) {
            for (int depth = 1; depth < maxR + 1; depth++) { //다리 긋기
                if (board[depth][next] != 0 || board[depth][next + 1] != 0) continue;

                board[depth][next] = next + 1;
                board[depth][next + 1] = next;
                backtracking(next + 1, n, r - 1);
                board[depth][next] = 0;
                board[depth][next + 1] = 0;
            }
        }
    }

    static boolean simulate(int start) {
        int cur = start;
        for (int depth = 1; depth < maxR + 1; depth++) {
            if (board[depth][cur] == 0) continue;
            cur = board[depth][cur];
        }

        if (cur == start) return true;
        return false;
    }
}
