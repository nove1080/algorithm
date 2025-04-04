package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1916 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static int st, en;

    static List<Node>[] edges;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to   = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            edges[from].add(new Node(to, cost));
        }

        String[] input = br.readLine().split(" ");
        st = Integer.parseInt(input[0]);
        en = Integer.parseInt(input[1]);

        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(st, 0));
        distance[st] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(distance[cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if (distance[next.num] <= distance[cur.num] + next.cost) continue;
                distance[next.num] = distance[cur.num] + next.cost;

                pq.add(new Node(next.num, distance[next.num]));
            }
        }

        return distance[en];
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
