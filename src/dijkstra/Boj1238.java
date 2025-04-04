package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1238 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m, x;

    static List<Node>[] edges;
    static int[][] distances;

    static int answer;
    public static void main(String[] args) throws Exception {
        init();
        //도착지에서 돌아가는 거리를 미리 모두 계산
        dijkstra(x);

        for (int i = 1; i < n + 1; i++) {
            if(i == x) continue;
            answer = Math.max(dijkstra(i), answer);
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        x = Integer.parseInt(input[2]);

        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to   = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            edges[from].add(new Node(to, cost));
        }

        distances = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
    }

    static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(start, 0));
        distances[start][start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(distances[start][cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if(distances[start][next.num] > distances[start][cur.num] + next.cost) {
                    distances[start][next.num] = distances[start][cur.num] + next.cost;
                    pq.add(new Node(next.num, distances[start][next.num]));
                }
            }
        }

        return distances[start][x] + distances[x][start];
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

}
