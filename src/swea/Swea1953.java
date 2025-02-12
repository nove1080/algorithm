//탈주범 검거
package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Swea1953 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int maxR, maxC;
    static int startR, startC;
    static int time;

    static Pipe[][] board;
    static boolean[][] vis; //최종 방문 개수를 파악할 배열
    static boolean[][] tempVis; //dfs 순회용 방문 배열

    //상하좌우
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();

            dfs(startR, startC, 1);

            int answer = getPossiblePositionCnt();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        startR = Integer.parseInt(input[2]);
        startC = Integer.parseInt(input[3]);

        time = Integer.parseInt(input[4]);

        vis = new boolean[maxR][maxC];
        tempVis = new boolean[maxR][maxC];

        board = new Pipe[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                int pipeType = Integer.parseInt(input[j]);
                board[i][j] = generatePipe(i, j, pipeType);
            }
        }
    }

    public static Pipe generatePipe(int r, int c, int pipeType) {
        if (pipeType == 1) { //상하좌우 파이프
            return new Pipe(r, c, pipeType, 4, new int[]{0, 1, 2, 3}, new int[]{0, 1, 2, 3});
        }
        if (pipeType == 2) { //상하 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{0, 1}, new int[]{0, 1});
        }
        if (pipeType == 3) { //좌우 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{2, 3}, new int[]{2, 3});
        }
        if (pipeType == 4) { //상우 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{0, 3}, new int[]{1, 2});
        }
        if (pipeType == 5) { //하우 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{1, 3}, new int[]{0, 2});
        }
        if (pipeType == 6) { //하좌 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{1, 2}, new int[]{0, 3});
        }
        if (pipeType == 7) { //상좌 파이프
            return new Pipe(r, c, pipeType, 2, new int[]{0, 2}, new int[]{1, 3});
        }

        return new Pipe(r, c, 0, 0, null, null); //없는 파이프
    }

    public static void dfs(int r, int c, int depth) {
        vis[r][c] = true;
        tempVis[r][c] = true;

        if (depth == time) {
            return;
        }

        Pipe pipe = board[r][c];
        for (int idx = 0; idx < pipe.hole; idx++) {
            int dir = pipe.movableDir[idx];
            int nr = r + moveR[dir];
            int nc = c + moveC[dir];

            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
            if (tempVis[nr][nc] || !board[nr][nc].isPassable(dir)) continue;

            tempVis[nr][nc] = true;
            dfs(nr, nc, depth + 1);
            tempVis[nr][nc] = false;
        }
    }

    static int getPossiblePositionCnt() {
        int answer = 0;
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                answer += vis[i][j] ? 1 : 0;
            }
        }

        return answer;
    }

    static class Pipe {
        int r, c;
        int type; // 1: 상하좌우 파이프, 2: 상하 파이프, ...
        int hole;
        int[] movableDir; //움직임 가능한 index
        Set<Integer> passableDir = new LinkedHashSet<>(); //통행 가능한 방향 : 해당 파이프를 지날 수 있는 통로?

        public Pipe(int r, int c, int type, int hole, int[] movableDir, int[] passableDir) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.hole = hole;
            this.movableDir = movableDir;

            if (hole > 0) {
                for (int dir : passableDir) {
                    this.passableDir.add(dir);
                }
            }
        }

        public boolean isPassable(int from) {
            return passableDir.contains(from);
        }
    }
}
