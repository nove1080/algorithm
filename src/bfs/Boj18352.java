//특정 거리의 도시 찾기
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj18352 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int numOfCity;
    static int numOfRoad;
    static int distance;
    static int startNode;

    static ArrayList<Integer>[] adjList;
    static boolean[] vis;

    public static void main(String[] args) throws Exception{
        init();
        List<Integer> result = bfs();
        Collections.sort(result);
        printAnswer(result);
    }

    public static void init() throws Exception{
        String[] input = br.readLine().split(" ");
        numOfCity = Integer.parseInt(input[0]);
        numOfRoad = Integer.parseInt(input[1]);
        distance  = Integer.parseInt(input[2]);
        startNode = Integer.parseInt(input[3]) - 1;

        vis = new boolean[numOfCity];
        adjList = new ArrayList[numOfCity];
        for (int i = 0; i < numOfCity; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < numOfRoad; i++) {
            input = br.readLine().split(" ");

            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;

            adjList[from].add(to);
        }
    }

    public static List<Integer> bfs() {
        ArrayList<Integer> result = new ArrayList();

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startNode, 0));
        vis[startNode] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.depth > distance) break;
            if (cur.depth == distance) result.add(cur.num + 1);

            for (int next : adjList[cur.num]) {
                if (vis[next]) continue;

                q.add(new Node(next, cur.depth + 1));
                vis[next] = true;
            }
        }

        return result;
    }

    private static void printAnswer(List<Integer> result) {
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (Integer i : result) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }

    static class Node {
        int num, depth;

        public Node(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }

    }

}
