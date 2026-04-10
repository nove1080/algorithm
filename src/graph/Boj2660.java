//회장 뽑기
// bfs: depth가 점수가 된다.
package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import java.io.*;
import java.util.*;

public class Boj2660 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static List<Integer>[] adjList;
    static int[][] scores;

    public static void main(String[] args) throws Exception {
        init();

        List<Integer> captains = new ArrayList<>();
        int minScore = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int score = bfs(i);

            if (minScore > score) {
                captains.clear();
                captains.add(i + 1);
                minScore = score;
            } else if (minScore == score) {
                captains.add(i + 1);
            }
        }

        sb.append(minScore).append(" ").append(captains.size()).append("\n");
        for (Integer captain : captains) {
            sb.append(captain).append(" ");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        String[] input = br.readLine().split(" ");
        int n1 = Integer.parseInt(input[0]) - 1;
        int n2 = Integer.parseInt(input[1]) - 1;
        while (n1 != -2 && n2 != -2) {
            adjList[n1].add(n2);
            adjList[n2].add(n1);

            input = br.readLine().split(" ");
            n1 = Integer.parseInt(input[0]) - 1;
            n2 = Integer.parseInt(input[1]) - 1;
        }

        scores = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(scores[i], Integer.MAX_VALUE);
        }
    }

    private static int bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        scores[start][start] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Integer next : adjList[cur.num]) {

                if (scores[start][next] <= cur.depth + 1) continue;

                q.add(new Node(next, cur.depth + 1));
                scores[start][next] = cur.depth + 1;
            }
        }

        int finalScore = 0;
        for (int i = 0; i < n; i++) {
            finalScore = Math.max(finalScore, scores[start][i]);
        }

        return finalScore;
    }

    private static class Node {
        int num, depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}

