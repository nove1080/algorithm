package dijkstra;

import java.io.*;
import java.util.*;

public class Boj9370 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int n, m, t;
    static int s, g, h;
    private static final int INF = 999_999_999;

    static List<Node>[] nodes;
    static int[] destinations;
    static int[] distance;
    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            init();
            int s2g = dijkstra(s, g);
            int g2h = dijkstra(g, h);

            int s2h = dijkstra(s, h);
            int h2g = dijkstra(h, g);

            for (int e : destinations) {
                int h2e = dijkstra(h, e);
                int g2e = dijkstra(g, e);
                int s2e = dijkstra(s, e);
                if ((s2g + g2h + h2e) == s2e || (s2h + h2g + g2e) == s2e) {
                    sb.append(e + 1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        t = Integer.parseInt(input[2]);

        input = br.readLine().split(" ");
        s = Integer.parseInt(input[0]) - 1;
        g = Integer.parseInt(input[1]) - 1;
        h = Integer.parseInt(input[2]) - 1;

        nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]) - 1;
            int n2 = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            nodes[n1].add(new Node(n2, cost));
            nodes[n2].add(new Node(n1, cost));
        }

        destinations = new int[t];
        for (int i = 0; i < t; i++) {
            destinations[i] = Integer.parseInt(br.readLine()) - 1;
        }
        Arrays.sort(destinations);

        distance = new int[n];
        Arrays.fill(distance, INF);
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(distance, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.num] < cur.cost) continue;
            if (cur.num == end) {
                distance[cur.num] = cur.cost;
                return cur.cost;
            }

            for (Node next : nodes[cur.num]) {
                if (distance[next.num] <= next.cost + cur.cost) continue;
                distance[next.num] = next.cost + cur.cost;
                pq.add(new Node(next.num, distance[next.num]));
            }
        }

        return INF;
    }

    static class Node {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}

