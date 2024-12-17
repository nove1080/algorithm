//구슬 탈출
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj13459 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static char[][] board;
    static boolean[][][][] vis;

    static int[] moveR = new int[]{-1, 1, 0, 0};
    static int[] moveC = new int[]{0, 0, -1, 1};

    static int rR, rC, bR, bC;
    static int portalR, portalC;

    public static void main(String[] args) throws Exception {
        init();
        int result = bfs();
        System.out.println(result);

    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        vis = new boolean[maxR][maxC][maxR][maxC];

        for (int i = 0; i < maxR; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                if (charArray[j] == 'R') {
                    rR = i;
                    rC = j;
                    board[i][j] = '.';
                } else if (charArray[j] == 'B') {
                    bR = i;
                    bC = j;
                    board[i][j] = '.';
                } else if (charArray[j] == 'O') {
                    portalR = i;
                    portalC = j;
                    board[i][j] = 'O';
                } else {
                    board[i][j] = charArray[j];
                }
            }
        }
    }

    public static int bfs() {
        Queue<Balls> q = new LinkedList<>();

        q.add(new Balls(rR, rC, bR, bC, 0));
        vis[rR][rC][bR][bC] = true;

        while (!q.isEmpty()) {
            Balls cur = q.poll();
//            System.out.println(cur);

            //종료조건
            if (cur.redR == portalR && cur.redC == portalC && (cur.blueR != portalR || cur.blueC != portalC)) {
                return 1;
            }
            if (cur.count > 10) {
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                Balls nextBalls = move(cur, dir);

                if (nextBalls == null) continue;
                if (vis[nextBalls.redR][nextBalls.redC][nextBalls.blueR][nextBalls.blueC]) continue;

                q.add(nextBalls);
                vis[nextBalls.redR][nextBalls.redC][nextBalls.blueR][nextBalls.blueC] = true;
            }
        }

        return 0;
    }

    /**
     * 공을 움직인다.<br>
     * - dir 방향으로 해당하는 곳으로 벽면까지 움직인다.
     * @param cur
     * @param dir
     */
    public static Balls move(Balls cur, int dir) {
        Balls result = new Balls(cur.redR, cur.redC, cur.blueR, cur.blueC, cur.count + 1);

        int startRR = result.redR;
        int startRC = result.redC;
        int startBR = result.blueR;
        int startBC = result.blueC;

        boolean isRedOut = false;
        boolean isBlueOut = false;

        while (true) {
            int nr = result.redR + moveR[dir];
            int nc = result.redC + moveC[dir];

            if (board[nr][nc] == '#') break;

            result.redR = nr;
            result.redC = nc;

            if (board[nr][nc] == 'O') {
                isRedOut = true;
                break;
            }
        }

        while (true) {
            int nr = result.blueR + moveR[dir];
            int nc = result.blueC + moveC[dir];

            if (board[nr][nc] == '#') break;

            result.blueR = nr;
            result.blueC = nc;

            if (board[nr][nc] == 'O') {
                isBlueOut = true;
                break;
            }
        }

        if (isBlueOut) return null;
        if (isRedOut && !isBlueOut) return result;

        //겹치는 경우 공의 위치 재조정
        if (result.redR == result.blueR && result.redC == result.blueC) {
            if (dir == 0) { //상
                if (startRR > startBR) result.redR++;
                else result.blueR++;
            }

            else if (dir == 1) {
                if (startRR > startBR) result.blueR--;
                else result.redR--;
            }

            else if (dir == 2) {
                if (startRC > startBC) result.redC++;
                else result.blueC++;
            }

            else if (dir == 3) {
                if (startRC > startBC) result.blueC--;
                else result.redC--;
            }
        }

        return result;
    }


    //빨간 공과 파란 공을 함께 움직이기 위해 Balls 클래스 생성
    static class Balls {
        int redR, redC;
        int blueR, blueC;
        int count;

        public Balls(int redR, int redC, int blueR, int blueC, int count) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Balls{" +
                "redR=" + redR +
                ", redC=" + redC +
                ", blueR=" + blueR +
                ", blueC=" + blueC +
                ", count=" + count +
                '}';
        }
    }
}
