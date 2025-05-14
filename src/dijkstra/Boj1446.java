package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1446 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, d;

    static final int INF = 10_000_000;

    static List<Node>[] edges;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);

        distance = new int[d + 1];
        Arrays.fill(distance, INF);

        edges = new ArrayList[d + 1];
        for (int i = 0; i < d + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            try {
                edges[from].add(new Node(to, cost));
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }

        for (int i = 0; i < d; i++) {
            edges[i].add(new Node(i + 1, 1)); //0 ~ d 까지 각각을 잇는 거리가 1인 간선을 추가함
        }
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);

        pq.add(new Node(0, 0));
        distance[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {

                if (next.num > d) continue;

                if (distance[next.num] <= next.cost + distance[cur.num]) continue;
                distance[next.num] = distance[cur.num] + next.cost;

                pq.add(new Node(next.num, distance[next.num]));
            }
        }

        return distance[d];
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
