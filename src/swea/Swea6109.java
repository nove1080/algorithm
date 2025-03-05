package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Swea6109 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static String command;

    static int[][] board;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            doCommand();
            sb.append("#").append(t).append("\n").append(returnAnswer());
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        command = input[1];

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static void doCommand() {
        if (command.equals("up")) {
            for (int c = 0; c < n; c++) {
                int prev = 0;
                int prevR = 0;
                for (int r = 0; r < n; r++) {
                    if (prev == board[r][c] && prev != 0) { //합치기
                        board[prevR][c] = prev *= 2;
                        board[r][c] = 0;

                        prev = 0;
                        prevR = -1;
                    }
                    if (board[r][c] != 0) {
                        prev = board[r][c];
                        prevR = r;
                    }
                }
            }
            //정렬
            for (int c = 0; c < n; c++) {
                int nonZeroValue = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int r = 0; r < n; r++) {
                    if (board[r][c] != 0) {
                        q.add(board[r][c]);
                        nonZeroValue++;
                    }
                }
                int r = 0;
                while (!q.isEmpty()) {
                    board[r][c] = q.poll();
                    r++;
                }
                for (int i = nonZeroValue; i < n; i++) {
                    board[r][c] = 0;
                    r++;
                }
            }
        }
        else if (command.equals("down")) {
            for (int c = 0; c < n; c++) {
                int prev = 0;
                int prevR = 0;
                for (int r = n - 1; r >= 0; r--) {
                    if (prev == board[r][c] && prev != 0) { //합치기
                        board[prevR][c] = prev *= 2;
                        board[r][c] = 0;

                        prev = 0;
                        prevR = -1;
                    }
                    if (board[r][c] != 0) {
                        prev = board[r][c];
                        prevR = r;
                    }
                }
            }
            //정렬
            for (int c = 0; c < n; c++) {
                int nonZeroValue = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int r = n - 1; r >= 0; r--) {
                    if (board[r][c] != 0) {
                        q.add(board[r][c]);
                        nonZeroValue++;
                    }
                }
                int r = n - 1;
                while (!q.isEmpty()) {
                    board[r][c] = q.poll();
                    r--;
                }
                for (int i = nonZeroValue; i < n; i++) {
                    board[r][c] = 0;
                    r--;
                }
            }
        }
        else if (command.equals("left")) {
            for (int r = 0; r < n; r++) {
                int prev = 0;
                int prevC = 0;
                for (int c = 0; c < n; c++) {
                    if (prev == board[r][c] && prev != 0) { //합치기
                        board[r][prevC] = prev *= 2;
                        board[r][c] = 0;

                        prev = 0;
                        prevC = -1;
                    }
                    if (board[r][c] != 0) {
                        prev = board[r][c];
                        prevC = c;
                    }
                }
            }
            //정렬
            for (int r = 0; r < n; r++) {
                int nonZeroValue = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int c = 0; c < n; c++) {
                    if (board[r][c] != 0) {
                        q.add(board[r][c]);
                        nonZeroValue++;
                    }
                }
                int c = 0;
                while (!q.isEmpty()) {
                    board[r][c] = q.poll();
                    c++;
                }
                for (int i = nonZeroValue; i < n; i++) {
                    board[r][c] = 0;
                    c++;
                }
            }
        }
        else { //right
            for (int r = 0; r < n; r++) {
                int prev = 0;
                int prevC = 0;
                for (int c = n - 1; c >= 0; c--) {
                    if (prev == board[r][c] && prev != 0) { //합치기
                        board[r][prevC] = prev *= 2;
                        board[r][c] = 0;

                        prev = 0;
                        prevC = -1;
                    }
                    if (board[r][c] != 0) {
                        prev = board[r][c];
                        prevC = c;
                    }
                }
            }
            //정렬
            for (int r = 0; r < n; r++) {
                int nonZeroValue = 0;
                Queue<Integer> q = new LinkedList<>();
                for (int c = n - 1; c >= 0; c--) {
                    if (board[r][c] != 0) {
                        q.add(board[r][c]);
                        nonZeroValue++;
                    }
                }
                int c = n - 1;
                while (!q.isEmpty()) {
                    board[r][c] = q.poll();
                    c--;
                }
                for (int i = nonZeroValue; i < n; i++) {
                    board[r][c] = 0;
                    c--;
                }
            }
        }
    }

    public static String returnAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
