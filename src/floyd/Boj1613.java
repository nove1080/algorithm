package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1613 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, k, s;

    static int[][] orders;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 0; i < n; i++) {
            bfs(i);
        }

        s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            String[] input = br.readLine().split(" ");
            int front = Integer.parseInt(input[0]) - 1;
            int end = Integer.parseInt(input[1]) - 1;

            sb.append(orders[front][end]).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        board = new int[n][n];
        orders = new int[n][n];

        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int front = Integer.parseInt(input[0]) - 1;
            int end = Integer.parseInt(input[1]) - 1;

            board[front][end] = -1;
            board[end][front] = 1;
        }
    }

    private static void bfs(int st) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(st, -1));
        q.add(new Node(st, 1));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int next = 0; next < n; next++) {
                if(board[cur.num][next] != cur.order || orders[st][next] != 0) continue;

                q.add(new Node(next, cur.order));
                orders[st][next] = cur.order;
                orders[cur.num][next] = cur.order;
            }
        }
    }

    static class Node {
        int num;
        int order;

        public Node(int num, int order) {
            this.num = num;
            this.order = order;
        }
    }
}

