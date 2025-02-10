package bfs.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class DFSWithRecursion {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int WALL = 0; //벽: 탐색 불가능

    static int maxR; //그래프의 최대 row 크기
    static int maxC; //그래프의 최대 column 크기

    static int[][] board; //그래프
    static boolean[][] visited; //그래프 상에서 방문 지점 체크

    //사방(상, 하, 좌, 우)에 대한 움직임
    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    //탐색을 시작하는 지점
    static int startR;
    static int startC;

    public static void main(String[] args) throws Exception {
        init();
        dfs(new Point(3, 2));
    }

    private static void init() throws Exception {
        /** 예시 입력 데이터 */
//        6 5
//        1 1 0 1 1
//        0 1 1 0 0
//        0 0 0 0 0
//        1 0 1 1 1
//        0 0 1 1 1
//        0 0 1 1 1
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new int[maxR][maxC];
        visited = new boolean[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    private static void dfs(Point cur) {
        System.out.printf("(%d, %d) -> ", cur.r, cur.c);
        for (int dir = 0; dir < 4; dir++) {
            int nextR = cur.r + moveR[dir];
            int nextC = cur.c + moveC[dir];

            if (nextR < 0 || nextR >= maxR || nextC < 0 || nextC >= maxC) continue;
            if (visited[nextR][nextC] || board[nextR][nextC] == WALL) continue;

            visited[nextR][nextC] = true;
            dfs(new Point(nextR, nextC));
        }
    }

    //좌표
    static class Point {
        int r, c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
