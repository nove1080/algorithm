package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea2001 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static int[][] board;

    static int flapperSize;

    static int[][] sumArr; //sumArr[i][j]: board[i][0] ~ board[i][j] 까지의 합

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            answer = calculateMaximumCount();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        flapperSize = Integer.parseInt(input[1]);

        board = new int[n][n];
        sumArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int prev = 0;
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if(j == 0) {
                    sumArr[i][j] = board[i][j];
                } else {sumArr[i][j] = sumArr[i][j - 1] + board[i][j];

                }
            }
        }
    }

    public static int calculateMaximumCount() {
        int result = 0;

        Point leftTop = new Point(0, 0);
        Point rightBottom = new Point(leftTop.r + flapperSize - 1, leftTop.c + flapperSize - 1);

        while(rightBottom.inRange()) { //파리채 방향을 아래로 내리며 진행
            while(rightBottom.inRange()) { //파리채 방향을 오른쪽으로 움직이며 진행
                result = Math.max(result, checkFlySize(leftTop, rightBottom));
                moveFlapperToRight(leftTop, rightBottom);
            }
            moveFlapperToDown(leftTop, rightBottom);
        }

        return result;
    }

    private static int checkFlySize(Point leftTop, Point rightBottom) {
        int flySize = 0;
        for (int i = 0; i < flapperSize; i++) {
            flySize += sumArr[leftTop.r + i][rightBottom.c];
            if (leftTop.c >= 1) {
                flySize -= sumArr[leftTop.r + i][leftTop.c - 1];
            }
        }
        return flySize;
    }

    private static void moveFlapperToDown(Point leftTop, Point rightBottom) {
        leftTop.r++;
        leftTop.c = 0;
        rightBottom.r++;
        rightBottom.c = flapperSize - 1;
    }

    private static void moveFlapperToRight(Point leftTop, Point rightBottom) {
        leftTop.c++;
        rightBottom.c++;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean inRange() {
            return !(r < 0 || r >= n || c < 0 || c >= n);
        }
    }
}
