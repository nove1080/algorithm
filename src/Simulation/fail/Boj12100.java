//2048 (Easy)
/**
 * 20^2 * 4^5 = 409600 < 1초 -> 모든 경우의 수를 시행해도 된다.
 */
package Simulation.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12100 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] directions = {0, 90, 180, 270};
    static int[][] board;
    static int max;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < 4; i++) {
            int[][] tempBoard = new int[n][n];
            for (int j = 0; j < n; j++) {
                tempBoard[j] = board[j].clone();
            }
            backtracking(directions[i], 1, tempBoard);
        }
        System.out.println(max);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void swipe(int degree, int[][] board) {
        if(degree == 0) {
            for (int i = 0; i < n; i++) {
                //step 1. 타일 합치기
                for (int j = 0; j < n - 1; j++) {
                    if(board[j][i] == 0) continue;
                    //현재 타일과 뒤따라 나올 0이 아닌 타일과 비교
                    for (int next = j + 1; next < n; next++) {
                        if (board[next][i] == 0) {
                            continue;
                        }
                        else if (board[next][i] == board[j][i]) {
                            board[j][i] *= 2;
                            board[next][i] = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                //step 2. 타일 벽면으로 이동시키기
                for (int j = 0; j < n; j++) {
                    SEARCH:
                    if(board[j][i] == 0) {
                        for (int k = j + 1; k < n; k++) {
                            if(board[k][i] != 0) {
                                //이동 시키기
                                board[j][i] = board[k][i];
                                board[k][i] = 0;
                                j = k - 1;
                                break SEARCH;
                            }
                        }
                    }
                }
            }
            printBoard(board);
        }
        else if (degree == 90) {
            for (int i = 0; i < n; i++) {
                //step 1. 타일 합치기
                for (int j = n - 1; j > 0; j--) {
                    if(board[i][j] == 0) continue;
                    //현재 타일과 뒤따라 나올 0이 아닌 타일과 비교
                    for (int next = j - 1; next >= 0; next--) {
                        if (board[i][next] == 0) {
                            continue;
                        }
                        else if (board[i][next] == board[i][j]) {
                            board[i][j] *= 2;
                            board[i][next] = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                //step 2. 타일 벽면으로 이동시키기
                for (int j = n - 1; j >= 0; j--) {
                    SEARCH:
                    if(board[i][j] == 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if(board[i][k] != 0) {
                                //이동 시키기
                                board[i][j] = board[i][k];
                                board[i][k] = 0;
                                j = k + 1;
                                break SEARCH;
                            }
                        }
                    }
                }
            }
        }
        else if (degree == 180) {
            for (int i = 0; i < n; i++) {
                //step 1. 타일 합치기
                for (int j = n - 1; j > 0; j--) {
                    if(board[j][i] == 0) continue;
                    //현재 타일과 뒤따라 나올 0이 아닌 타일과 비교
                    for (int next = j - 1; next >= 0; next--) {
                        if (board[next][i] == 0) {
                            continue;
                        }
                        else if (board[next][i] == board[j][i]) {
                            board[j][i] *= 2;
                            board[next][i] = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                //step 2. 타일 벽면으로 이동시키기
                for (int j = n - 1; j >= 0; j--) {
                    SEARCH:
                    if(board[j][i] == 0) {
                        for (int k = j - 1; k >= 0; k--) {
                            if(board[k][i] != 0) {
                                //이동 시키기
                                board[j][i] = board[k][i];
                                board[k][i] = 0;
                                j = k + 1;
                                break SEARCH;
                            }
                        }
                    }
                }
            }
        }
        else if (degree == 270) {
            for (int i = 0; i < n; i++) {
                //step 1. 타일 합치기
                for (int j = 0; j < n - 1; j++) {
                    if(board[i][j] == 0) continue;
                    //현재 타일과 뒤따라 나올 0이 아닌 타일과 비교
                    for (int next = j + 1; next < n; next++) {
                        if (board[i][next] == 0) {
                            continue;
                        }
                        else if (board[i][next] == board[i][j]) {
                            board[i][j] *= 2;
                            board[i][next] = 0;
                            break;
                        } else {
                            break;
                        }
                    }
                }
                //step 2. 타일 벽면으로 이동시키기
                for (int j = 0; j < n; j++) {
                    SEARCH:
                    if(board[i][j] == 0) {
                        for (int k = j + 1; k < n; k++) {
                            if(board[i][k] != 0) {
                                //이동 시키기
                                board[i][j] = board[i][k];
                                board[i][k] = 0;
                                j = k - 1;
                                break SEARCH;
                            }
                        }
                    }
                }
            }
        }
    }

    static void backtracking(int dir, int count, int[][] board) {
        if(count == 6) {
            for (int[] ints : board) {
                for (int anInt : ints) {
                    if(max < anInt) max = anInt;
                }
            }
            return;
        }
        swipe(dir, board);
        int[][] backup = new int[n][n];
        for (int j = 0; j < n; j++) {
            backup[j] = board[j].clone();
        }
        for (int i = 0; i < 4; i++) {
            backtracking(directions[i], count + 1, board);
            board = backup;
        }
    }

    static void printBoard(int[][] board) {
        System.out.println("========== printBoard() ==========");
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
