package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2252 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;

    static ArrayList<Integer>[] adjList;
    static int[] inbounds;

    public static void main(String[] args) throws Exception {
        init();
        search();
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        inbounds = new int[n];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int out = Integer.parseInt(input[0]) - 1;
            int in = Integer.parseInt(input[1]) - 1;

            adjList[out].add(in);
            inbounds[in]++;
        }
    }

    public static void search() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inbounds[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            sb.append(cur + 1).append(" ");


            for (Integer next : adjList[cur]) {
                inbounds[next]--;

                if (inbounds[next] == 0) q.add(next);
            }
        }
    }
}
