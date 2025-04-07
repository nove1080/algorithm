package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16118 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;

    static int[] foxDist;
    /**
     * 늑대의 이동 경로 비용을 저장
     * wolfDist[0][]: 늑대가 해당 지점을 걸어서 도착한 비용
     * wolfDist[1][]: 늑대가 해당 지점을 뛰어서 도착한 비용
     */
    static int[][] wolfDist;

    static List<Node>[] edges;

    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        dijkstraForFox();
        dijkstraForWolf();
        for (int i = 0; i < n + 1; i++) {
            if(foxDist[i] < Math.min(wolfDist[0][i], wolfDist[1][i])) answer++;
        }

        System.out.println(answer);
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
        wolfDist[0][1] = 0; //시작점으로 걸어들어오는 경우를 방지
        wolfDist[1][1] = Integer.MAX_VALUE; //시작점으로 뛰어들어오는 경우는 허용 -> 이 경우에는 시작점을 재방문하더라도 최소 비용으로 갱신될 여지가 존재하기 때문에 허용하는 것

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (wolfDist[cur.count][cur.num] < cur.cost) continue;

            for (Node next : edges[cur.num]) {
                if (cur.count == 0) { //달려갈 차례
                    if (wolfDist[1][next.num] <= wolfDist[0][cur.num] + next.cost / 2) continue; //이전에 다음지점까지 달려가보았을 때의 비용 <= 현재 위치까지 걸어온 비용 + 현재지점에서 다음지점까지 뛰어간 비용

                    wolfDist[1][next.num] = wolfDist[0][cur.num] + next.cost / 2;
                    pq.add(new Node(next.num, wolfDist[1][next.num], 1));
                } else { //걸어갈 차례
                    if (wolfDist[0][next.num] <= wolfDist[1][cur.num] + next.cost * 2) continue;

                    wolfDist[0][next.num] = wolfDist[1][cur.num] + next.cost * 2;
                    pq.add(new Node(next.num, wolfDist[0][next.num], 0));
                }
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
    }
}