//미세먼지 안녕!
package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17144 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC, t;

    // 동, 남, 서, 북
    public static int[] moveR = {0, 1, 0, -1};
    public static int[] moveC = {1, 0, -1, 0};

    static int[][] board;

    static int airR1, airC1, airR2, airC2;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < t; i++) {
            run();
        }

        int answer = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                answer += board[i][j];
            }
        }

        System.out.println(answer + 2);

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
        airMachine();
//        applyUpperAirPurifier();
//        applyLowerAirPurifier();
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

        //윗 공기청정기 : 윗쪽
        for (int i = 1; i <= airR1; i++) {
            int tmp = board[airR1 - i][maxC - 1];
            board[airR1 - i][maxC - 1] = prev;
            prev = tmp;
        }

        //윗 공기청정기 : 왼쪽
        for (int i = 1; i < maxC; i++) {
            int tmp = board[0][maxC - i - 1];
            board[0][maxC - i - 1] = prev;
            prev = tmp;
        }

        //윗 공기청정기 : 아랫쪽
        for (int i = 1; i < airR1; i++) {
            int tmp = board[i][0];
            board[i][0] = prev;
            prev = tmp;
        }
    }

    public static void applyLowerAirPurifier() {
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

        for (int i = 1; i < maxR - airR2; i++) {
            int tmp = board[airR2 + i][maxC - 1];
            board[airR2 + i][maxC - 1] = prev;
            prev = tmp;
        }

        for (int i = 1; i < maxC; i++) {
            int tmp = board[maxR - 1][maxC - i - 1];
            board[maxR - 1][maxC - i - 1] = prev;
            prev = tmp;
        }

        for (int i = 2; i < maxR - airR2; i++) {
            int tmp = board[maxR - i][0];
            board[maxR - i][0] = prev;
            prev = tmp;
        }
    }

    public static void airMachine() {
        int temp = board[0][0];
        int curR = 0;
        int curC = 0;

        for (int dir = 0; dir < 4; dir++) {
            while(true) {
                int nr = moveR[dir] + curR;
                int nc = moveC[dir] + curC;
                if (nr == airR1 && nc == airC1) continue;
                if (nr < 0 || nc < 0 || nr > airR1 || nc >= maxC) break;
                board[nr][nc] = board[curR][curC];

                curR = nr;
                curC = nc;
            }
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
