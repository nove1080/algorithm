package dijkstra;

import java.io.*;
import java.util.*;

public class Boj17835 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m, k;
    static int[] starts;
    static List<Node>[] nodes;
    static long[] distances;

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        printAnswer();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int from = Integer.parseInt(input[1]) - 1;
            int to = Integer.parseInt(input[0]) - 1;
            int cost = Integer.parseInt(input[2]);

            nodes[from].add(new Node(to, cost));
        }

        starts = new int[k];
        input = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            starts[i] = Integer.parseInt(input[i]) - 1;
        }

        distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1.cost, n2.cost));
        for (int start : starts) {
            pq.add(new Node(start, 0));
            distances[start] = 0;
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distances[cur.num] < cur.cost) continue;

            for (Node next : nodes[cur.num]) {
                if (distances[cur.num] + next.cost >= distances[next.num]) continue;
                distances[next.num] = distances[cur.num] + next.cost;
                pq.add(new Node(next.num, distances[next.num]));
            }
        }
    }

    private static void printAnswer() {
        int city = 1;
        long distance = -1L;
        for (int i = 0; i < n; i++) {
            if (distance < distances[i]) {
                city = i + 1;
                distance = distances[i];
            }
        }

        System.out.println(city);
        System.out.println(distance);
    }

    static class Node {
        int num;
        long cost;

        public Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}

