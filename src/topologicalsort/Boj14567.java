package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj14567 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static ArrayList<Integer>[] adjList;
    static boolean[] vis;
    /**
     * index = 노드 번호
     * value = 진입 차수
     */
    static int[] inBounds;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 0; i < n; i++) {
            if(inBounds[i] == 0 && !vis[i]) {
                bfs(i);
            }
        }

        for (int i : answer) {
            System.out.println(i);
        }
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);


        vis = new boolean[n];
        inBounds = new int[n];
        answer = new int[n];
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;

            adjList[from].add(to);
            inBounds[to]++;
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();

            for (int next : adjList[start]) {
                inBounds[next]--;
                answer[next] = Math.max(answer[next], answer[cur] + 1);

                if (inBounds[next] == 0) {
                    q.add(next);
                    vis[next] = true;
                }
            }
        }
    }
}
