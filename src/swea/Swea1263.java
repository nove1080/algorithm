package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Swea1263 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < n; i++) {
                answer = Math.min(bfs(i), answer);
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = Integer.MAX_VALUE;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);

        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isConnected = input[j + n * i + 1].equals("1");
                if (isConnected) {
                    adjList[i].add(j);
                }
            }
        }
    }

    public static int bfs(int st) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(st, 0));
        distance[st] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Integer next : adjList[cur.num]) {
                int nextDepth = cur.depth + 1;

                if (distance[next] <= nextDepth) continue;

                q.add(new Node(next, nextDepth));
                distance[next] = nextDepth;
            }
        }

        return Arrays.stream(distance).sum();
    }

    static class Node {
        int num;
        int depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
