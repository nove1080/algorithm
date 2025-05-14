package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1238_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int INF = 10_000_000;
    static int n, m, end;

    static List<Node>[] edges;

    static int[][] distances;

    public static void main(String[] args) throws Exception {
        init();

        //목적지에서 출발지로 가는 최단 경로를 미리 계산
        dijkstra(end, 0);
        
        int answer = 0;
        for (int start = 0; start < n; start++) {
            answer = Math.max(dijkstra(start, end), answer);
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        end = Integer.parseInt(input[2]) - 1;

        edges = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            edges[from].add(new Node(to, cost));
        }

        distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], INF);
        }
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(start, 0));
        distances[start][start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(distances[start][cur.num] <= cur.cost) continue; //여기에 등호가 있으면 올바르게 동작하지 않는 이유는?

            for (Node next : edges[cur.num]) {

                if(distances[start][next.num] <= distances[start][cur.num] + next.cost) continue;
                distances[start][next.num] = distances[start][cur.num] + next.cost;

                pq.add(new Node(next.num, distances[start][next.num]));
            }
        }

        return distances[start][end] + distances[end][start];
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
