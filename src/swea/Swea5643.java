package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Swea5643 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static ArrayList<Integer>[] adjList;

    static int[] visCount;

    static boolean[][] vis;
    static int answer;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < n; i++) {
                bfs(i);
            }

            for (int i = 0; i < n; i++) {
                if (visCount[i] == n) {
                    answer++;
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

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
