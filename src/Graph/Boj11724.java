//연결 요소의 개수
package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11724 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer>[] edges;
    static boolean[] vis;
    static int n, m;
    static int count;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 1; i < n + 1; i++) {
            if (vis[i]) {
                continue;
            }
            count++;
            dfs(i);
        }
        System.out.println(count);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n + 1];
        vis = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edges[v1].add(v2);
            edges[v2].add(v1);
        }

    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : edges[cur]) {
                if (vis[next]) continue;
                q.add(next);
                vis[next] = true;
            }
        }
    }

    static void dfsWithRecursion(int v) {
        if (vis[v]) return;
        vis[v] = true;
        for (int i : edges[v]) {
            if (vis[i]) continue;
            dfsWithRecursion(i);
        }
    }

    static void dfs(int v) {
        Stack<Integer> s = new Stack<>();
        s.add(v);
        vis[v] = true;

        while (!s.isEmpty()) {
            int cur = s.pop();
            for (int next : edges[cur]) {
                if(vis[next]) continue;
                s.add(next);
                vis[next] = true;
            }
        }
    }
}
