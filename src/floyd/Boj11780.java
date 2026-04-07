package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.*;
import java.util.*;

public class Boj11780 {

    private static final int MAX_DIST = 100_000_000;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static int[][] dist, next;

    public static void main(String[] args) throws Exception {
        init();
        floyd();
        printAnswer();
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n][n];
        next = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], MAX_DIST);
        }
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            if (cost < dist[from][to]) {
                dist[from][to] = cost;
                next[from][to] = to;
            }
        }
    }

    private static void floyd() {
        for (int i = 0; i < n; i++) { //i: 거쳐갈 정점의 번호
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) continue;
                    if (dist[j][k] > dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                        next[j][k] = next[j][i];
                    }
                }
            }
        }
    }

    private static void printAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer.append(dist[i][j] == MAX_DIST ? 0 : dist[i][j]).append(" ");
            }
            answer.append("\n");
        }

        //경로 출력
        for (int st = 0; st < n; st++) { //출발
            for (int en = 0; en < n; en++) { //도착
                if (dist[st][en] == MAX_DIST) {
                    answer.append(0);
                } else {
                    int cur = st;
                    List<Integer> route = new ArrayList<>();
                    while (cur != en) {
                        route.add(cur);
                        cur = next[cur][en];
                    }
                    route.add(en);

                    answer.append(route.size()).append(" ");
                    for (Integer node : route) {
                        answer.append(node + 1).append(" ");
                    }
                }
                answer.append("\n");
            }
        }

        System.out.println(answer);
    }
}

