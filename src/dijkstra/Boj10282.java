package dijkstra;

import java.io.*;
import java.util.*;

public class Boj10282 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    static int n, d, start;
    static List<Node>[] nodes;
    static int[] distances;

    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            init();
            dijkstra();
            checkVirus();
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        start = Integer.parseInt(input[2]) - 1;

        nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < d; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[1]) - 1;
            int to = Integer.parseInt(input[0]) - 1;
            int cost = Integer.parseInt(input[2]);

            nodes[from].add(new Node(to, cost));
        }

        distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(start, 0));
        distances[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distances[cur.num] < cur.cost) continue;

            for (Node next : nodes[cur.num]) {
                if (distances[next.num] <= next.cost + cur.cost) continue;
                distances[next.num] = next.cost + cur.cost;
                pq.add(new Node(next.num, distances[next.num]));
            }
        }
    }

    private static void checkVirus() {
        int maxDistance = 0;
        int count = 0;
        for (int distance : distances) {
            if (distance == Integer.MAX_VALUE) continue;
            maxDistance = Math.max(maxDistance, distance);
            count++;
        }

        sb.append(count).append(" ").append(maxDistance).append("\n");
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


