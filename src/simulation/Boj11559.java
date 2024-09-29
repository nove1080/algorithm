//Puyo Puyo

/**
 * idea
 * 1) 보드판의 모든 원소에 대하여 bfs -> 4군데 이상 방문 시 폭발
 * <p>
 * 시간복잡도 < 1초
 * -> 12 * 6 * bfs의 시간복잡도 * 12(최대 12연쇄?) < 1초
 */
package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj11559 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[][] board = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, 1, 0, -1};

    static int ans;

    static void init() throws Exception {
        for (int i = 0; i < 12; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 6; j++) {
                board[i][j] = tmp[j];
            }
        }
    }

    static void puyo() {
        do {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, board[i][j]);
                    }
                }
            }
            ans++;
        } while (nextStatus());
    }

    // (r,c)에서 bfs 수행
    static void bfs(int r, int c, char color) {
        Queue<Point> q = new LinkedList();
        q.add(new Point(r, c));
        visited[r][c] = true;

        List<Point> bombList = new ArrayList();
        while (!q.isEmpty()) {
            Point cur = q.poll();
            bombList.add(cur);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + moveX[i];
                int ny = cur.y + moveY[i];

                if (nx < 0 || ny < 0 || nx > 11 || ny > 5) continue;
                if (visited[nx][ny] || board[nx][ny] != color) continue;

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }

        if (bombList.size() >= 4) bomb(bombList);
    }

    //폭발이 일어난 곳에 'X' 표시
    static void bomb(List<Point> bombList) {
        for (Point p : bombList) {
            board[p.x][p.y] = 'X';
        }
    }

    static boolean nextStatus() {
        boolean isChanged = false;
        //폭발로 생긴 빈 자리를 파악 후 블록을 내린다
        for (int i = 0; i < 6; i++) {
            int idx = 0;
            char[] newCol = new char[12];
            for (int j = 0; j < 12; j++) {
                if (board[j][i] == 'X') {
                    isChanged = true;
                    continue;
                }
                newCol[idx++] = board[j][i];
            }

            for (int j = 0; j < idx; j++) {
                board[11 - j][i] = newCol[idx - j - 1];
            }

            for (int j = idx; j < 12; j++) {
                board[11 - j][i] = '.';
            }

        }
        clearVisited();
        return isChanged;
    }

    //Tip : Java에서 배열을 선언 시 기본값으로 원소가 초기화된다.
    static void clearVisited() {
        visited = new boolean[12][6];
    }

    public static void main(String[] args) throws Exception {
        init();
        puyo();
        System.out.println(ans - 1);
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
