package graph;

import java.util.*;
import java.io.*;

public class boj2458 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;

    static ArrayList<Integer>[] adjList;

    static int[] visCount;

    static boolean[][] vis;
    static int answer;

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 0; i < n; i++) {
            bfs(i);
        }

        for (int i = 0; i < n; i++) {
            if (visCount[i] == n) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        visCount = new int[n];

        adjList = new ArrayList[n];
        vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");

            int shorter = Integer.parseInt(line[0]) - 1;
            int longer = Integer.parseInt(line[1]) - 1;

            adjList[shorter].add(longer);
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visCount[start]++;
        vis[start][start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer next : adjList[cur]) {
                if(vis[start][next]) continue;

                q.add(next);
                visCount[start]++;
                visCount[next]++;
                vis[start][next] = true;
                vis[next][start] = true;
            }
        }
    }

}
