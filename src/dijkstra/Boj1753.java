package dijkstra;
//최단경로
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Boj1753 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int v, e;
    static int start;

    static List<Node>[] nodes;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        for (int dis : distance) {
            sb.append(dis != Integer.MAX_VALUE ? dis : "INF").append("\n");
        }
        System.out.print(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        v = Integer.parseInt(input[0]);
        e = Integer.parseInt(input[1]);
        start = Integer.parseInt(br.readLine()) - 1;

        nodes = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            input = br.readLine().split(" ");
            nodes[Integer.parseInt(input[0]) - 1].add(new Node(Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2])));
        }

        distance = new int[v];
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);

        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(distance[cur.v] < cur.w) continue;

            for (Node next : nodes[cur.v]) {
                if(distance[cur.v] + next.w < distance[next.v]) {
                    distance[next.v] = distance[cur.v] + next.w; 
                    pq.add(new Node(next.v, distance[next.v])); //누적 비용을 담아서 PQ에 삽입
                }
            }
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
