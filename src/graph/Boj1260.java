//DFS와 BFS
package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj1260 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder bfsPrinter = new StringBuilder();
    static StringBuilder dfsPrinter = new StringBuilder();
    static int n, m, start;
    //인접 행렬
    static int[][] adjMatrix;
    static boolean[] vis;

    public static void main(String[] args) throws Exception {
        init();
//        dfsWithRecursion(start);
        dfs(start);
        vis = new boolean[n + 1];
        bfs(start);

        System.out.println(dfsPrinter);
        System.out.println(bfsPrinter);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        adjMatrix = new int[n + 1][n + 1];
        vis = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            adjMatrix[v1][v2]++;
            adjMatrix[v2][v1]++;
        }
    }

    static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        vis[v] = true;
        bfsPrinter.append(v).append(" ");

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (int next = 1; next < n + 1; next++) {
                if (adjMatrix[cur][next] == 0 || vis[next]) continue;
                q.add(next);
                vis[next] = true;
                bfsPrinter.append(next).append(" ");
            }
        }
    }

    static void dfsWithRecursion(int v) {
        if(vis[v]) return;
        vis[v] = true;
        dfsPrinter.append(v).append(" ");
        for (int next = 1; next < n + 1; next++) {
            if(adjMatrix[v][next] == 0 || vis[next]) continue;
            dfsWithRecursion(next);
        }
    }

//    static void dfs(int v) {
//        Stack<Integer> s = new Stack<>();
//        s.add(v);
//        vis[v] = true;
//
//        while (!s.isEmpty()) {
//            Integer cur = s.pop();
//            dfsPrinter.append(cur).append(" ");
//            for (int next = n; next > 0; next--) {
//                if(adjMatrix[cur][next] == 0 || vis[next]) continue;
//                s.add(next);
//                vis[next] = true;
//            }
//        }
//    }


    static void dfs(int v) {
        Stack<Integer> s = new Stack<>();
        s.add(v);
        while (!s.isEmpty()) {
            Integer cur = s.pop();
            if(vis[cur]) continue;
            vis[cur] = true;
            dfsPrinter.append(cur).append(" ");

            for (int next = n; next > 0; next--) {
                if(vis[next]) continue;
                s.push(next);
            }
        }
    }
}
