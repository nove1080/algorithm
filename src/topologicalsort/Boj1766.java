package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Boj1766 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static List<Integer>[] tables;

    /**
     * value: key를 선행 조건으로 가지고 있는 숫자들의 리스트
     */
    static Map<Integer, List<Integer>> maps = new HashMap<>();
    static int n, m;

    public static void main(String[] args) throws Exception {
        init();
        List<Integer> result = topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (Integer num : result) {
            sb.append(num + 1).append(" ");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        tables = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tables[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int first = Integer.parseInt(input[0]) - 1;
            int second = Integer.parseInt(input[1]) - 1;

            tables[second].add(first);
            if (!maps.containsKey(first)) {
                maps.put(first, new ArrayList<>());
            }
            maps.get(first).add(second);
        }
    }

    static List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n1 - n2); //작은 수 우선

        for (int i = 0; i < n; i++) {
            if (tables[i].isEmpty()) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            result.add(cur);

            List<Integer> nextProblems = maps.get(cur);
            if (nextProblems != null) {
                for (Integer nextProblem : nextProblems) {
                    tables[nextProblem].remove(cur);

                    if (tables[nextProblem].isEmpty()) {
                        pq.add(nextProblem);
                    }
                }
            }
        }

        return result;
    }
}
