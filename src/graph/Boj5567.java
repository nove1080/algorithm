//결혼식
package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj5567 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static ArrayList<Integer>[] adjList;
    static boolean[] vis;

    public static void main(String[] args) throws Exception {
        init();
        bfs();
        //dfs();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken()) - 1;
            int num2 = Integer.parseInt(st.nextToken()) - 1;

            adjList[num1].add(num2);
            adjList[num2].add(num1);
        }

    }

    static void bfs() {
        int count = 0;
        Queue<Integer> friend = new LinkedList<>();
        vis[0] = true;
        for (Integer i : adjList[0]) {
            if (vis[i]) continue;
            friend.add(i);
            count++;
            vis[i] = true;
        }

        while (!friend.isEmpty()) {
            Integer another = friend.poll();
            for (Integer i : adjList[another]) {
                if (vis[i]) continue;
                count++;
                vis[i] = true;
            }
        }
    }

    static void dfs() {
        int count = 0;
        Stack<Integer> s = new Stack<>();
        vis[0] = true;
        for (Integer i : adjList[0]) {
            s.add(i);
            count++;
            vis[i] = true;
        }

        while (!s.isEmpty()) {
            Integer cur = s.pop();
            for (Integer i : adjList[cur]) {
                if (vis[i]) continue;
                count++;
                vis[i] = true;
            }
        }

        System.out.println(count);
    }
}
