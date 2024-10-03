//다리 만들기 2 10:50
package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj17472 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int[][] tempBoard;

    static int[][] board;
    static boolean[][] vis;
    static int[] moveR = new int[]{1, 0, -1, 0};
    static int[] moveC = new int[]{0, 1, 0, -1};

    static int sector = 0;

    static List<Bridge> bridges = new ArrayList<>();

    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        init();
//        printBoard(board);

        addAllBridges();
//        System.out.println("print All Available Bridges");
//        printBridges(bridges);
//        System.out.println("print All Available Bridges Done!!!");

        for (int i = 0; i < bridges.size(); i++) {
            dfs(0, i, new int[sector - 1]);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] inputs = br.readLine().split(" ");
        maxR = Integer.parseInt(inputs[0]);
        maxC = Integer.parseInt(inputs[1]);

        tempBoard = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < maxC; j++) {
                tempBoard[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        calculateBoardSector();
    }

    public static void dfs(int depth, int start, int[] bridgesIndexes) {
        if (depth == sector - 1) {
            int result = getLength(bridgesIndexes);
            if(result != -1) {
                answer = Math.min(answer, result);
            }
            return;
        }

        bridgesIndexes[depth] = start;
        for (int i = start; i < bridges.size(); i++) {
            dfs(depth + 1, i + 1, bridgesIndexes);
        }
    }

    public static int getLength(int[] bridgesIndexes) {
        ArrayList<Integer>[] connectTable = new ArrayList[sector];
        for (int i = 0; i < sector; i++) {
            connectTable[i] = new ArrayList();
        }
        int length = 0;
        for (int i = 0; i < bridgesIndexes.length; i++) {
            Bridge b = bridges.get(bridgesIndexes[i]);
            connectTable[b.startSector - 1].add(b.endSector - 1);
            connectTable[b.endSector - 1].add(b.startSector - 1);
            length += b.length;
        }

        boolean[] vis = new boolean[sector];
        //섬 전체가 연결되지 않으면 -1을 반환
        Integer start;
        try {
          start = connectTable[0].get(0);
        } catch (Exception e) {
            return -1;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();

            for(Integer next : connectTable[cur]) {
                if (vis[next]) continue;
                q.add(next);
                vis[next] = true;
            }
        }

        for (int i = 0; i < sector; i++) {
            if (!vis[i]) return -1;
        }

//        System.out.println("SAVE !!!!!");
//        ArrayList<Bridge> bs = new ArrayList<>();
//        for(int idx : bridgesIndexes) {
//            bs.add(bridges.get(idx));
//        }
//
//        printBridges(bs);
//        System.out.println("SAVE DONE !!!!!");

        return length;
    }

    public static void calculateBoardSector() {
        Queue<Point> q = new LinkedList<>();

        board = new int[maxR][maxC];
        vis = new boolean[maxR][maxC];

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (tempBoard[i][j] == 1 && !vis[i][j]) {
                    sector++;
                    vis[i][j] = true;
                    q.add(new Point(i, j));

                    while (!q.isEmpty()) {
                        Point cur = q.poll();
                        board[cur.r][cur.c] = sector;

                        for (int dir = 0; dir < 4; dir++) {
                            int nr = cur.r + moveR[dir];
                            int nc = cur.c + moveC[dir];

                            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                            if (tempBoard[nr][nc] != 1 || vis[nr][nc]) continue;

                            q.add(new Point(nr, nc));
                            vis[nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    public static void addAllBridges() {
        addAllRowBridges();
        addAllColBridges();
    }

    public static void addAllRowBridges() {
        for (int i = 0; i < maxR; i++) {
            int prevC = 0;
            int prevSector = 0;

            for (int j = 0; j < maxC; j++) {
                int curSector = board[i][j];

                if (curSector == 0) {
                    continue;
                }

                if (prevSector == 0 || prevSector == curSector) { //다리 시작점 갱신
                    prevSector = curSector;
                    prevC = j;
                    continue;
                }

                //다리를 찾음
                int length = j - prevC - 1;
                if (length > 1) {
                    bridges.add(new Bridge(new Point(i, prevC), new Point(i, j), length, prevSector, curSector));
                }
                prevSector = curSector;
                prevC = j;
            }
        }
    }

    public static void addAllColBridges() {
        for (int i = 0; i < maxC; i++) {
            int prevR = 0;
            int prevSector = 0;

            for (int j = 0; j < maxR; j++) {
                int curSector = board[j][i];

                if (curSector == 0) {
                    continue;
                }

                if (prevSector == 0 || prevSector == curSector) { //다리 시작점 갱신
                    prevSector = curSector;
                    prevR = j;
                    continue;
                }

                //다리를 찾음
                int length = j - prevR - 1;
                if (length > 1) {
                    bridges.add(new Bridge(new Point(prevR, i), new Point(j, i), length, prevSector, curSector));
                }
                prevSector = curSector;
                prevR = j;
            }
        }
    }

    public static void printBoard(int[][] board) {
        System.out.println("printBoard...");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("printBoard done!");
    }

    public static void printBridges(List<Bridge> bridges) {
        bridges.stream().forEach(b -> System.out.println(b));
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Bridge {
        Point start, end;
        int length;
        int startSector;
        int endSector;

        public Bridge(Point start, Point end, int length, int startSector, int endSector) {
            this.start = start;
            this.end = end;
            this.length = length;
            this.startSector = startSector;
            this.endSector = endSector;
        }

        public String toString() {
            return "length: %s       (%s, %s) -> (%s, %s)".formatted(length, start.r, start.c, end.r, end.c);
        }
    }
}
