//미세먼지 안녕!

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17144_na {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC, t;
    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};
    static int[][] board;

    static int airR1, airC1, airR2, airC2;

    public static void main(String[] args) throws Exception {
        init();
        run();

        printBoard();
    }

    private static void run() {
        int[][] tmpBoard = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board[i][j] != -1) {
                    spread(i, j, tmpBoard);
                }
            }
        }

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                board[i][j] += tmpBoard[i][j];
            }
        }

        applyUpperAirPurifier();
        applyLowerAirPurifier();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);
        t = Integer.parseInt(input[2]);

        boolean isFirst = true;
        board = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == -1 && isFirst) {
                    airR1 = i;
                    airC1 = j;

                    isFirst = false;
                } else if (board[i][j] == -1) {
                    airR2 = i;
                    airC2 = j;
                }
            }
        }
    }

    /**
     * (r, c)의 미세먼지를 확산
     * @param r
     * @param c
     */
    public static void spread(int r, int c, int[][] tmpBoard) {
        int spreadAmount = board[r][c] / 5;

        for (int dir = 0; dir < 4; dir++) {
            int nr = moveR[dir] + r;
            int nc = moveC[dir] + c;

            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
            if (board[nr][nc] == -1) continue;
            tmpBoard[r][c] -= spreadAmount;
            tmpBoard[nr][nc] += spreadAmount;
        }
    }

    public static void applyUpperAirPurifier() {
        //윗 공기청정기 : 오른쪽
        System.out.println("before right");
        printBoard();
        int prev = 0;
        for (int i = 1; i < maxC; i++) {
            if (board[airR1][i - 1] == -1) {
                prev = board[airR1][i];
                board[airR1][i] = 0;
                continue;
            }
            int tmp = board[airR1][i];
            board[airR1][i] = prev;
            prev = tmp;
        }
        System.out.println("[prev] = " + prev);
        System.out.println("before up");
        printBoard();


        //윗 공기청정기 : 윗쪽
        for (int i = 1; i <= airR1; i++) {
            int tmp = board[airR1 - i][maxC - 1];
            board[airR1 - i][maxC - 1] = prev;
            prev = tmp;
        }

        System.out.println("before left");
        printBoard();

        //윗 공기청정기 : 왼쪽
        for (int i = 1; i < maxC; i++) {
            int tmp = board[0][maxC - i - 1];
            board[0][maxC - i - 1] = prev;
            prev = tmp;
        }

        System.out.println("before down");
        printBoard();
        //윗 공기청정기 : 아랫쪽
        for (int i = 1; i < airR1; i++) {
            int tmp = board[i][0];
            board[i][0] = prev;
            prev = tmp;
        }
    }

    public static void applyLowerAirPurifier() {
        System.out.println("before right");
        printBoard();
        int prev = 0;
        for (int i = 1; i < maxC; i++) {
            if (board[airR2][i - 1] == -1) {
                prev = board[airR2][i];
                board[airR2][i] = 0;
                continue;
            }
            int tmp = board[airR2][i];
            board[airR2][i] = prev;
            prev = tmp;
        }

        System.out.println("before down");
        printBoard();
        for (int i = 0; i < airR2; i++) {
            int tmp = board[airR2 + i][maxC - 1];
            board[airR2 + i][maxC - 1] = prev;
            prev = tmp;
        }

        System.out.println("before left");
        printBoard();

        for (int i = 1; i < maxC; i++) {
            int tmp = board[0][maxC - i - 1];
            board[0][maxC - i - 1] = prev;
            prev = tmp;
        }

        System.out.println("[prev] = " + prev);
        System.out.println("before up");
        printBoard();

        for (int i = 1; i <= airR2; i++) {
            int tmp = board[airR2 - i][maxC - 1];
            board[airR2 - i][maxC - 1] = prev;
            prev = tmp;
        }
    }

    private static void printBoard() {
        System.out.println("[print]");
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
