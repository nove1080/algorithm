package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1277 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, w;
    static double m;

    static final double INF = 400_000.0;

    static Point[] points;
    static List<Node>[] edges;
    static double[] distance;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);
        m = Double.parseDouble(br.readLine());

        points = new Point[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);

            points[i] = new Point(r, c);
        }

        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < w; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;

            //이미 연결된 전선은 cost 를 0으로 표현
            edges[from].add(new Node(to, 0));
            edges[to].add(new Node(from, 0));
        }

        distance = new double[n];
        Arrays.fill(distance, INF);

        //사전에 연결되지 않은 간선의 cost 는 두 정점 간의 거리로 표현
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = calcDistance(points[i], points[j]);
                if (dist > m) continue;
                edges[i].add(new Node(j, dist));
                edges[j].add(new Node(i, dist));
            }
        }
    }

    private static double calcDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.r - p2.r, 2) + Math.pow(p1.c - p2.c, 2));
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Double.compare(n1.cost, n2.cost));
        pq.add(new Node(0, 0));
        distance[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if(distance[next.num] <= distance[cur.num] + next.cost) continue;

                distance[next.num] = distance[cur.num] + next.cost;
                pq.add(new Node(next.num, distance[next.num]));
            }
        }

        return (int) (distance[n - 1] * 1000);
    }

    static class Node {
        int num;
        double cost;

        public Node(int num, double cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
