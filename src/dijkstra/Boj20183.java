package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj20183 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m, st, en;
    static long money;
    static final int INF = 200_000_000;

    static List<Node>[] edges;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra(st, en));
    }


    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        st = Integer.parseInt(input[2]) - 1;
        en = Integer.parseInt(input[3]) - 1;
        money = Long.parseLong(input[4]);

        edges = new ArrayList[m];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int n1 = Integer.parseInt(input[0]) - 1;
            int n2 = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            edges[n1].add(new Node(n2, cost));
            edges[n2].add(new Node(n1, cost));
        }
    }

    public static int dijkstra(int start, int end) {
        int[] costs = new int[n]; //최대 요금의 최소값
        Arrays.fill(costs, INF);

        PriorityQueue<Alley> pq = new PriorityQueue<>((n1, n2) -> n1.maxCost - n2.maxCost);
        pq.add(new Alley(start, 0, money));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Alley cur = pq.poll();

            if(cur.num == end) continue; //도착지점에 도착한 경우
            if(costs[end] <= cur.maxCost) continue; //더 큰 돈을 지불하게 될 경우

            for (Node next : edges[cur.num]) {
                long money = cur.money - next.cost;

                // 돈이 부족한 경우
                if(money < 0) continue;

                //최대값 중 최소값으로 갱신
                costs[next.num] = Math.min(costs[next.num], Math.max(cur.maxCost, next.cost));
                pq.add(new Alley(next.num, Math.max(cur.maxCost, next.cost), money));
            }
        }

        logging(costs);

        return costs[end] < INF ? costs[end] : -1;
    }

    private static void logging(int[] costs) {
        System.out.println("COST =======");
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + " 의 비용 " + costs[i]);
        }
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static class Alley {
        int num;
        int maxCost;
        long money;

        public Alley(int num, int maxCost, long money) {
            this.num = num;
            this.maxCost = maxCost;
            this.money = money;
        }
    }
}
