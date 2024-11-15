//케빈 베이컨의 6단계 법칙
package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1389 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] board;
    static int n;
    static int minKevinNum = Integer.MAX_VALUE;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        System.out.println(answer + 1);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        board = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int i1 = Integer.parseInt(input[0]) - 1;
            int i2 = Integer.parseInt(input[1]) - 1;
            board[i1][i2] = true;
            board[i2][i1] = true;
        }

    }

    public static void dfs(int target) {
        int kevinNum = 0;
        boolean[] vis = new boolean[n];

        Queue<Bacon> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (board[target][i] && !vis[i]) {
                q.add(new Bacon(i, 1));
                vis[i] = true;
            }
        }

        while(!q.isEmpty()) {
            Bacon cur = q.poll();
            kevinNum += cur.depth;
            for (int i = 0; i < n; i++) {
                if (board[cur.num][i] && !vis[i]) {
                    q.add(new Bacon(i, cur.depth + 1));
                    vis[i] = true;
                }
            }
        }

        if (kevinNum < minKevinNum) {
            minKevinNum = kevinNum;
            answer = target;
        }
    }

    static class Bacon {
        int num, depth;

        Bacon(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
