package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16118_Int {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;

    static int[] foxDist;
    static int[][] wolfDist;

    static List<Node>[] edges;

    static int answer;

    public static void main(String[] args) throws Exception {
        init();
//        logging();
        dijkstraForFox();
        dijkstraForWolf();
        for (int i = 0; i < n + 1; i++) {
            if(foxDist[i] < Math.min(wolfDist[0][i], wolfDist[1][i])) answer++;
        }

        System.out.println(answer);
    }

    private static void logging() {
        dijkstraForFox();
        System.out.println();
        for (int i = 0; i < n + 1; i++) {
            System.out.print(foxDist[i] + " ");
        }

        dijkstraForWolf();
        System.out.println();
        System.out.println("RUN WOLF");
        for (int i = 0; i < n + 1; i++) {
            System.out.print(wolfDist[0][i] + " ");
        }
        System.out.println();

        System.out.println("WALK WOLF");
        for (int i = 0; i < n + 1; i++) {
            System.out.print(wolfDist[1][i] + " ");
        }
        System.out.println();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int c1 = Integer.parseInt(input[0]);
            int c2 = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            edges[c1].add(new Node(c2, cost * 2));
            edges[c2].add(new Node(c1, cost * 2));
        }

        foxDist = new int[n + 1];
        Arrays.fill(foxDist, Integer.MAX_VALUE);
        wolfDist = new int[2][n + 1];
        Arrays.fill(wolfDist[0], Integer.MAX_VALUE);
        Arrays.fill(wolfDist[1], Integer.MAX_VALUE);
    }

    static void dijkstraForFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Double.compare(n1.cost, n2.cost));
        pq.add(new Node(1, 0));
        foxDist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (foxDist[cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if (foxDist[next.num] <= foxDist[cur.num] + next.cost) continue;

                foxDist[next.num] = foxDist[cur.num] + next.cost;
                pq.add(new Node(next.num, foxDist[next.num]));
            }
        }
    }

    static void dijkstraForWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.cost));
        pq.add(new Node(1, 0));
        wolfDist[0][1] = 0;
//        wolfDist[1][1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (wolfDist[cur.count][cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if (cur.count % 2 == 1) { //느리게 가야하는 경우
//                    if (wolfDist[1][next.num] <= wolfDist[0][cur.num] + next.cost * 2) continue;
//                    wolfDist[1][next.num] = wolfDist[0][cur.num] + next.cost * 2;
                    int newCost = wolfDist[1][cur.num] + (next.cost * 2);
                    if (wolfDist[0][next.num] > newCost) {
                        wolfDist[0][next.num] = newCost;
                        pq.add(new Node(next.num, newCost, 0));
                    }
                } else { //빠르게 가는 경우
//                    if (wolfDist[0][next.num] <= wolfDist[1][cur.num] + next.cost / 2) continue;
//                    wolfDist[0][next.num] = wolfDist[1][cur.num] + next.cost / 2;
                    int newCost = wolfDist[0][cur.num] + (next.cost / 2);
                    if (wolfDist[1][next.num] > newCost) {
                        wolfDist[1][next.num] = newCost;
                        pq.add(new Node(next.num, newCost, 1));
                    }
                }

//                pq.add(new Node(next.num, wolfDist[cur.count][next.num], (cur.count + 1) % 2));
            }
        }
    }

    static class Node {
        int num;
        int cost;
        int count;

        public Node(int num, int cost) {
            this(num, cost, 0);
        }

        public Node(int num, int cost, int count) {
            this.num = num;
            this.cost = cost;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", cost=" + cost +
                    ", count=" + count +
                    '}';
        }
    }
}