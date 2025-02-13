package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea4014 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer = 0;

    static int n;
    static int length; // 경사로의 길이

    static int[][] board;

    public static void main(String[] args) throws Exception {
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            init();
            for(int i = 0; i < n; i++) {
                answer += isRunwayRow(i, new boolean[n]) ? 1 : 0;
                answer += isRunwayCol(i, new boolean[n]) ? 1 : 0;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        length = Integer.parseInt(input[1]);

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static boolean isRunwayRow(int row, boolean[] installed) {
        int prev = board[row][0];
        for(int i = 1; i < n; i++) {
            if (prev == board[row][i] + 1) { //낮은 지형
                //앞의 [경사로 길이]만큼 높이가 같은지 확인
                if (i + length <= n && !installed[i]) {
                    int next = board[row][i];
                    installed[i] = true;

                    for (int j = 1; j < length; j++) {
                        if(next != board[row][i + j] || installed[i + j]) return false;
                        next = board[row][i + j];
                        installed[i + j] = true;
                    }

                    prev = board[row][i];
                    i = i + length - 1;
                } else {
                    return false;
                }
            } else if (prev == board[row][i] - 1) { //높은 지형
                if (i - length >= 0 && !installed[i]) {
                    int tmpPrev = prev;
                    for (int j = 1; j <= length; j++) {
                        if(tmpPrev != board[row][i - j] || installed[i - j]) return false;

                        tmpPrev = board[row][i - j];
                        installed[i - j] = true;
                    }
                } else {
                    return false;
                }
            } else if (prev != board[row][i]) { //건설 불가
                return false;
            }
            prev = board[row][i];
        }

        return true;
    }

    public static boolean isRunwayCol(int col, boolean[] installed) {
        int prev = board[0][col];
        for (int i = 1; i < n; i++) {
            if (prev == board[i][col] + 1) { //낮은 지형
                //앞의 [경사로 길이]만큼 높이가 같은지 확인
                if (i + length <= n && !installed[i]) {
                    int next = board[i][col];
                    installed[i] = true;

                    for (int j = 1; j < length; j++) {
                        if (next != board[i + j][col] || installed[i + j])
                            return false;
                        next = board[i + j][col];
                        installed[i + j] = true;
                    }
                    prev = board[i][col];
                    i = i + length - 1;
                } else {
                    return false;
                }
            } else if (prev == board[i][col] - 1) { //높은 지형
                if (i - length >= 0 && !installed[i]) {
                    int tmpPrev = prev;
                    for (int j = 1; j <= length; j++) {
                        if (tmpPrev != board[i - j][col] || installed[i - j]) return false;

                        tmpPrev = board[i - j][col];
                        installed[i - j] = true;
                    }
                } else {
                    return false;
                }
            } else if (prev != board[i][col]) { //건설 불가
                return false;
            }
            prev = board[i][col];
        }

        return true;
    }
}
