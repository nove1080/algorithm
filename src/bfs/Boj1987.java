package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Boj1987 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;

    static char[][] board;
    static int[] moveR = {1, 0, -1, 0};
    static int[] moveC = {0, 1, 0, -1};

    static int loopCnt;
    static int maxDataStructureSize;

    static int recursionCnt;
    static int recursionStackSize;
    static int maxRecursionStackSize;
    static int dfsRecursionAnswer;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println("===============BFS===============");
        System.out.println(bfs());
        System.out.println("LOOP COUNT = " + loopCnt);
        System.out.println("MAX QUEUE SIZE = " + maxDataStructureSize);

        System.out.println("==========DFS with Stack==========");
        loopCnt = 0;
        maxDataStructureSize = 0;
        System.out.println(dfsWithStack());
        System.out.println("LOOP COUNT = " + loopCnt);
        System.out.println("MAX STACK SIZE = " + maxDataStructureSize);

        System.out.println("========DFS with Recursion========");
        dfsWithRecursion(new Point(0, 0, 0, 0).addHistory(board[0][0]));
        System.out.println(dfsRecursionAnswer);
        System.out.println("RECURSION COUNT = " + recursionCnt);
        System.out.println("MAX RECURSION STACK SIZE = " + maxRecursionStackSize);

        dfsWithRecursion(new Point(0, 0, 0, 0).addHistory(board[0][0]));
        System.out.println(dfsRecursionAnswer);
    }

    public static void init() throws Exception {
        /**
         * BFS: 메모리 초과 예시 입력 데이터
         * 20 20
         * ABCDEFGHIJKLMNOPQRST
         * BCDEFGHIJKLMNOPQRSTU
         * CDEFGHIJKLMNOPQRSTUV
         * DEFGHIJKLMNOPQRSTUVW
         * EFGHIJKLMNOPQRSTUVWX
         * FGHIJKLMNOPQRSTUVWXY
         * GHIJKLMNOPQRSTUVWXYZ
         * HIJKLMNOPQRSTUVWXYZA
         * IJKLMNOPQRSTUVWXYZAB
         * JKLMNOPQRSTUVWXYZABC
         * KLMNOPQRSTUVWXYZABCD
         * LMNOPQRSTUVWXYZABCDE
         * MNOPQRSTUVWXYZABCDEF
         * NOPQRSTUVWXYZABCDEFG
         * OPQRSTUVWXYZABCDEFGH
         * PQRSTUVWXYZABCDEFGHI
         * QRSTUVWXYZABCDEFGHIJ
         * RSTUVWXYZABCDEFGHIJK
         * STUVWXYZABCDEFGHIJKL
         * TUVWXYZABCDEFGHIJKLM
         */
        String[] input = br.readLine().split(" ");
        maxR = Integer.parseInt(input[0]);
        maxC = Integer.parseInt(input[1]);

        board = new char[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < maxC; j++) {
                board[i][j] = input[j].charAt(0);
            }
        }
    }

    public static int bfs() {
        int result = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, 0).addHistory(board[0][0]));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            loopCnt++;

            result = Math.max(cur.size, result);
            maxDataStructureSize = Math.max(maxDataStructureSize, q.size());

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (cur.has(board[nr][nc])) continue;

                q.add(new Point(nr, nc, cur.history, cur.size).addHistory(board[nr][nc]));
            }
        }

        return result;
    }

    public static int dfsWithStack() {
        int result = 0;
        Stack<Point> stk = new Stack<>();
        stk.add(new Point(0, 0, 0, 0).addHistory(board[0][0]));

        while (!stk.isEmpty()) {
            Point cur = stk.pop();
            loopCnt++;

            result = Math.max(cur.size, result);
            maxDataStructureSize = Math.max(maxDataStructureSize, stk.size());

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + moveR[dir];
                int nc = cur.c + moveC[dir];

                if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
                if (cur.has(board[nr][nc])) continue;

                stk.add(new Point(nr, nc, cur.history, cur.size).addHistory(board[nr][nc]));
            }
        }

        return result;
    }

    public static void dfsWithRecursion(Point cur) {
        recursionCnt++;
        dfsRecursionAnswer = Math.max(cur.size, dfsRecursionAnswer);

        for (int dir = 0; dir < 4; dir++) {
            int nr = cur.r + moveR[dir];
            int nc = cur.c + moveC[dir];

            if (nr < 0 || nc < 0 || nr >= maxR || nc >= maxC) continue;
            if (cur.has(board[nr][nc])) continue;

            recursionStackSize++;
            maxRecursionStackSize = Math.max(maxRecursionStackSize, recursionStackSize);
            dfsWithRecursion(new Point(nr, nc, cur.history, cur.size).addHistory(board[nr][nc]));
            recursionStackSize--;
        }
    }

    static class Point {
        int r, c;
        int history = 0;
        int size = 0;

        public Point(int r, int c, int history, int size) {
            this.r = r;
            this.c = c;
            this.history = history;
            this.size = size;
        }

        public boolean has(char alphabet) {
            return (history & (1 << (alphabet - 'A'))) != 0;
        }

        public Point addHistory(char alphabet) {
            history |= (1 << (alphabet - 'A'));
            size++;
            return this;
        }
    }
}
