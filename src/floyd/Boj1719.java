package floyd;

import java.io.*;
import java.util.*;

public class Boj1719 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 200_000;
    static int n, m;
    static int[][] distances;
    static int[][] next;

    public static void main(String[] args) throws Exception {
        init();
        floyd();
        printRoute();
    }

    private static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], MAX);
        }
        next = new int[n][n];

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]) - 1;
            int n2 = Integer.parseInt(input[1]) - 1;
            int cost = Integer.parseInt(input[2]);

            distances[n1][n2] = cost;
            distances[n2][n1] = cost;
            next[n1][n2] = n2;
            next[n2][n1] = n1;
        }
    }

    private static void floyd() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (distances[j][k] > distances[j][i] + distances[i][k]) {
                        distances[j][k] = distances[j][i] + distances[i][k];
                        next[j][k] = next[j][i];
                    }
                }
            }
        }
    }

    private static void printRoute() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(i == j ? "-" : next[i][j] + 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
