package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj1707 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int tc;

    static int[] color;
    static boolean[] vis;
    static List<Integer>[] board;

    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            init();
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && !bfs(j)) {
                    flag = false;
                    break;
                }
            }

            sb.append(flag ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        color = new int[n];
        vis = new boolean[n];

        board = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            board[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0]) - 1;
            int num2 = Integer.parseInt(input[1]) - 1;

            board[num1].add(num2);
            board[num2].add(num1);
        }
    }

    static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = -1;
        vis[start] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();

            for (int next : board[cur]) {
                if (vis[next]) {
                    if (color[next] == color[cur]) return false;
                } else {
                    color[next] = color[cur] * -1;
                    vis[next] = true;
                    q.add(next);
                }
            }
        }

        return true;
    }

}
