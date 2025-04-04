package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj11779 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static int[] distance;
    static int[] path;

    static List<Node>[] nodes;
    static int st, en;

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        System.out.println(distance[en]);

        List<Integer> answers = new ArrayList<>();
        int cur = en;
        int length = 1;
        while (true) {
            answers.add(cur + 1);
            length++;
            cur = path[cur];
            if(cur == st) break;
        }
        answers.add(st + 1);

        for (int i = answers.size() - 1; i >= 0; i--) {
            sb.append(answers.get(i)).append(" ");
        }

        System.out.println(length);
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");

            int from = Integer.parseInt(input[0]) - 1;
            int to   = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            nodes[from].add(new Node(to, cost));
        }

        String[] input = br.readLine().split(" ");
        st = Integer.parseInt(input[0]) - 1;
        en = Integer.parseInt(input[1]) - 1;

        distance = new int[n];
        path = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(st, 0));
        distance[st] = 0;
        path[st] = st;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.cost > distance[cur.num]) continue;

            for (Node next : nodes[cur.num]) {
                if(distance[next.num] > distance[cur.num] + next.cost) {
                    path[next.num] = cur.num;
                    distance[next.num] = distance[cur.num] + next.cost;
                    pq.add(new Node(next.num, distance[next.num]));
                }
            }
        }
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
