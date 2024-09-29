//소문난 칠공주
//bfs (X) -> 
//backtracking -> 시간복잡도 가능
//선택된 동선 -> 1로 킨다
// 동선 선택 기준
// 0이 하나라도 존재하는 동선 -> 동선에 추가
package backtracking;

import java.io.*;

public class Boj1941 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static char[][] board;
    // isVisited?????????
    static int cnt; // 경로의 수
    static boolean[][] isSelected;
    static Point[] path;
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {1, 0, -1, 0};

    static void init() throws Exception {
        board = new char[5][5];
        isSelected = new boolean[5][5];
        path = new Point[7];

        for (int i = 0; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                board[i][j] = tmp[j];
            }
        }
    }

    static void backtracking(int x, int y, int length, int sNum) {
        //base condition
        if (length == 7) {
            if (sNum >= 4) {
                if (save()) cnt++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (nextX < 0 || nextX > 4 || nextY < 0 || nextY > 4) continue;
            path[length] = new Point(nextX, nextY);
            if (board[nextX][nextY] == 'S')
                backtracking(nextX, nextY, length + 1, sNum + 1);
            else
                backtracking(nextX, nextY, length + 1, sNum);
        }
    }

    static boolean save() {
        boolean isValid = false;
        for (Point p : path) {
            if (!isSelected[p.x][p.y]) isValid = true;
        }

        if (isValid) {
            for (Point p : path)
                isSelected[p.x][p.y] = true;
            //Test
            printBoard();
        }

        return isValid;
    }

    static void printBoard() {
        System.out.println("========= print(" + cnt + ") =========");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean flag = false;
                for (Point p : path) {
                    if (i == p.x && j == p.y) {
                        System.out.print("1 ");
                        flag = true;
                        break;
                    }
                }
                if (!flag) System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                backtracking(i, j, 0, 0);
            }
        }
        System.out.println(cnt);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
