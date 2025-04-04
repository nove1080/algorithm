package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Boj1504 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, e;

    static ArrayList<Node>[] edges;
    static int[] distance;

    static int n1, n2;

    static final int INF = 200_000_000;

    public static void main(String[] args) throws Exception {
        init();
        long dis1 = 0;
        dis1 += dijkstra(1, n1);
        dis1 += dijkstra(n1, n2);
        dis1 += dijkstra(n2, n);

        long dis2 = 0;
        dis2 += dijkstra(1, n2);
        dis2 += dijkstra(n2, n1);
        dis2 += dijkstra(n1, n);

        long answer = Math.min(dis1, dis2);
        System.out.println(answer < INF ? answer : -1);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        e = Integer.parseInt(input[1]);

        edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]);
            int n2 = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            edges[n1].add(new Node(n2, cost));
            edges[n2].add(new Node(n1, cost));
        }

        input = br.readLine().split(" ");

        n1 = Integer.parseInt(input[0]);
        n2 = Integer.parseInt(input[1]);
    }

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(start, 0));

        distance = new int[n + 1];
        Arrays.fill(distance, INF);

        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(distance[cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if (distance[next.num] <= distance[cur.num] + next.cost) continue;
                distance[next.num] = distance[cur.num] + next.cost;

                pq.add(new Node(next.num, distance[next.num]));
            }
        }

        return distance[end];
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
