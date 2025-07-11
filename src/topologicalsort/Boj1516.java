package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj1516 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    static int[] times;
    static int[] indegrees;
    static List<Integer>[] list;

    public static void main(String[] args) throws Exception {
        init();
        int[] result = topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int time : result) {
            sb.append(time).append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        times = new int[n];
        indegrees = new int[n];
        list = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int cur = 0; cur < n; cur++) {
            String[] input = br.readLine().split(" ");

            int initialTime = Integer.parseInt(input[0]);
            times[cur] += initialTime;

            for (int j = 1; j < input.length - 1; j++) {
                int priority = Integer.parseInt(input[j]) - 1;

                list[priority].add(cur);
                indegrees[cur]++;
            }
        }
    }

    static int[] topologicalSort() {
        int[] result = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
                result[i] = times[i];
            }
        }

        while (!q.isEmpty()) {
            Integer cur = q.poll();

            for (Integer next : list[cur]) {
                result[next] = Math.max(result[next], times[next] + result[cur]);
                indegrees[next]--;

                if (indegrees[next] == 0) q.add(next);
            }
        }

        return result;
    }

}
