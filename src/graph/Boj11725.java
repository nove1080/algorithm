//11725: 트리의 부모 찾기
package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11725 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer>[] nodes;
    static boolean[] vis;
    static int[] parents;
    static int n;
    public static void main(String[] args) throws Exception {
        init();
        bfs();
        printAnswer();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        nodes = new ArrayList[n + 1];
        vis = new boolean[n + 1];
        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            nodes[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            nodes[node1].add(node2);
            nodes[node2].add(node1);
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int parent : parents) {
            if(parent == 0) continue;
            sb.append(parent)
              .append("\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;

        while(!q.isEmpty()) {
            Integer cur = q.poll();
            for (Integer node : nodes[cur]) {
                if(vis[node]) continue;
                parents[node] = cur; //부모 기록
                q.add(node);
                vis[node] = true;
            }
        }
    }
}
