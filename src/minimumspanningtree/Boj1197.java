package minimumspanningtree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1197 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int answer;
    static int v, e;

    static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

    static int[] parents;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(kruskal());
    }

    public static void init() throws Exception {
        answer = 0;
        pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

        String[] input = br.readLine().split(" ");
        v = Integer.parseInt(input[0]);
        e = Integer.parseInt(input[1]);

        for (int i = 0; i < e; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]) - 1;
            int v2 = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            pq.add(new Edge(v1, v2, cost));
        }

        heights = new int[v];
        parents = new int[v];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public static int kruskal() {
        int cost = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.v1) == find(e.v2)) continue;
            union(e.v1, e.v2);
            cost += e.cost;
        }

        return cost;
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (heights[rootA] == heights[rootB]) {
            parents[rootA] = rootB;
            heights[rootB]++;
        } else if (heights[rootA] > heights[rootB]) {
            parents[rootB] = rootA;
        } else {
            parents[rootA] = rootB;
        }
    }

    public static int find(int a) {
        while (parents[a] != a) {
            a = parents[a];
        }

        return a;
    }

    static class Edge {
        int v1;
        int v2;
        int cost;

        public Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
    }
}
