package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Swea1238 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int start;
    static ArrayList<Integer>[] adjList;
    static int[] vis;

    public static void main(String[] args) throws Exception {

        for (int t = 1; t <= 10; t++) {
            init();

            bfs(start);

            int maxDepth = 0;
            for (int i = 0; i < vis.length; i++) {
                if(vis[i] >= maxDepth) {
                    answer = i;
                    maxDepth = vis[i];
                }
            }
            sb.append("#").append(t).append(" ").append(answer + 1).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        adjList = new ArrayList[100];
        vis = new int[100];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList();
        }

        String[] input = br.readLine().split(" ");
        start = Integer.parseInt(input[1]) - 1;

        input = br.readLine().split(" ");
        for (int i = 0; i < input.length / 2; i++) {
            int from = Integer.parseInt(input[i * 2]) - 1;
            int to   = Integer.parseInt(input[i * 2 + 1]) - 1;

            if(!adjList[from].contains(to)) adjList[from].add(to);
        }
    }

    public static void bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 1));
        vis[start] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Integer next : adjList[cur.num]) {
                if(vis[next] != 0) continue;

                q.add(new Node(next, cur.depth + 1));
                vis[next] = cur.depth + 1;
            }
        }
    }

    static class Node {
        int num, depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
