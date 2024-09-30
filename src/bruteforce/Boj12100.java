//2048 (Easy) 1726
package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj12100 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] board;
    static int answer = 0;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < 4; i++) {
            dfs(0, swipe(i, board));
        }

        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static void dfs(int depth, int[][] board) {
        count++;
        if (depth == 4) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, board[i][j]);
                }
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            dfs(depth + 1, swipe(i, board));
        }
    }

    public static void printBoard(int[][] board) {
        System.out.println("======= printBoard =======");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] swipe(int dir, int[][] curBoard) {

        if (dir == 0) { //상
            return swipeUp(curBoard);
        } else if (dir == 1) { //하
            return swipeDown(curBoard);
        } else if (dir == 2) { //좌
            return swipeLeft(curBoard);
        } else { //우
            return swipeRight(curBoard);
        }
    }

    private static int[][] swipeUp(int[][] curBoard) {
        int[][] result = new int[n][n];
        Stack<Integer>[] stacks = new Stack[n];

        for (int c = 0; c < n; c++) {
            stacks[c] = new Stack<>();
            boolean canMerge = true;

            for (int r = 0; r < n; r++) {
                int cur = curBoard[r][c];
                if (cur == 0) continue;

                if (stacks[c].isEmpty()) {
                    stacks[c].add(cur);
                    canMerge = true;
                } else {
                    Integer prev = stacks[c].peek();
                    if (canMerge && prev == cur) { //병합
                        stacks[c].add(cur + stacks[c].pop());
                        canMerge = false;
                    } else {
                        stacks[c].add(cur);
                        canMerge = true;
                    }
                }
            }

            //결과 반영
            int idx = 1;
            int size = stacks[c].size();
            while(!stacks[c].isEmpty()) {
                result[size - idx++][c] = stacks[c].pop();
            }
        }
        return result;
    }


    private static int[][] swipeDown(int[][] curBoard) {
        int[][] result = new int[n][n];
        Stack<Integer>[] stacks = new Stack[n];

        for (int c = 0; c < n; c++) {
            stacks[c] = new Stack<>();
            boolean canMerge = true;

            for (int r = n - 1; r >= 0; r--) {
                int cur = curBoard[r][c];
                if (cur == 0) continue;

                if (stacks[c].isEmpty()) {
                    stacks[c].add(cur);
                    canMerge = true;
                } else {
                    Integer prev = stacks[c].peek();
                    if (canMerge && prev == cur) { //병합
                        stacks[c].add(cur + stacks[c].pop());
                        canMerge = false;
                    } else {
                        stacks[c].add(cur);
                        canMerge = true;
                    }
                }
            }

            //결과 반영
            int idx = 0;
            int size = stacks[c].size();
            while(!stacks[c].isEmpty()) {
                result[n - (size - idx++)][c] = stacks[c].pop();
            }
        }
        return result;
    }

    private static int[][] swipeLeft(int[][] curBoard) {
        int[][] result = new int[n][n];
        Stack<Integer>[] stacks = new Stack[n];

        for (int r = 0; r < n; r++) {
            stacks[r] = new Stack<>();
            boolean canMerge = true;

            for (int c = 0; c < n; c++) {
                int cur = curBoard[r][c];
                if (cur == 0) continue;

                if (stacks[r].isEmpty()) {
                    stacks[r].add(cur);
                    canMerge = true;
                } else {
                    Integer prev = stacks[r].peek();
                    if (canMerge && prev == cur) { //병합
                        stacks[r].add(cur + stacks[r].pop());
                        canMerge = false;
                    } else {
                        stacks[r].add(cur);
                        canMerge = true;
                    }
                }
            }

            //결과 반영
            int idx = 1;
            int size = stacks[r].size();
            while(!stacks[r].isEmpty()) {
                result[r][size - idx++] = stacks[r].pop();
            }
        }
        return result;
    }

    private static int[][] swipeRight(int[][] curBoard) {
        int[][] result = new int[n][n];
        Stack<Integer>[] stacks = new Stack[n];

        for (int r = 0; r < n; r++) {
            stacks[r] = new Stack<>();
            boolean canMerge = true;

            for (int c = n - 1; c >= 0; c--) {
                int cur = curBoard[r][c];
                if (cur == 0) continue;

                if (stacks[r].isEmpty()) {
                    stacks[r].add(cur);
                    canMerge = true;
                } else {
                    Integer prev = stacks[r].peek();
                    if (canMerge && prev == cur) { //병합
                        stacks[r].add(cur + stacks[r].pop());
                        canMerge = false;
                    } else {
                        stacks[r].add(cur);
                        canMerge = true;
                    }
                }
            }

            //결과 반영
            int idx = 0;
            int size = stacks[r].size();
            while(!stacks[r].isEmpty()) {
                result[r][n - (size - idx++)] = stacks[r].pop();
            }
        }
        return result;
    }
}
