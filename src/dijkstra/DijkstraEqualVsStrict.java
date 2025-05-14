package dijkstra;

import java.util.*;

public class DijkstraEqualVsStrict {
    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] graph;
    static int n = 4;

    public static void main(String[] args) {
        buildGraph();

        int start = 0;
        int end = 3;

        int[] resultStrict = dijkstraStrict(start);
        int[] resultEqual = dijkstraEqual(start);

        System.out.println("=== 최단 거리 (Strict < 사용) ===");
        System.out.println(Arrays.toString(resultStrict));

        System.out.println("=== 최단 거리 (<= 사용) ===");
        System.out.println(Arrays.toString(resultEqual));

        if (!Arrays.equals(resultStrict, resultEqual)) {
            System.out.println("🚨 정답이 다릅니다! `<=` 사용 시 문제가 생깁니다.");
        } else {
            System.out.println("✅ 정답은 같지만, 안정성을 위해 `<` 사용 권장됩니다.");
        }
    }

    static void buildGraph() {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // 0 → 1 (비용 1)
        graph[0].add(new Node(1, 1));

        // 0 → 2 (비용 1)
        graph[0].add(new Node(2, 1));

        // 1 → 3 (비용 1)
        graph[1].add(new Node(3, 1));

        // 2 → 3 (비용 100) ← 이게 함정!
        graph[2].add(new Node(3, 100));
    }

    static int[] dijkstraStrict(int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.num] < now.cost) continue; // < 만 사용

            for (Node next : graph[now.num]) {
                if (dist[next.num] > dist[now.num] + next.cost) {
                    dist[next.num] = dist[now.num] + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist;
    }

    static int[] dijkstraEqual(int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.num] <= now.cost && now.num != start) continue; // <= 사용

            for (Node next : graph[now.num]) {
                if (dist[next.num] > dist[now.num] + next.cost) {
                    dist[next.num] = dist[now.num] + next.cost;
                    pq.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist;
    }

    static class Node {
        int num, cost;
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
