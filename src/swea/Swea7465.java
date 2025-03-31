package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Swea7465 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n, m;

    static int[] arr;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < m; i++) {
                String[] input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]) - 1;
                int b = Integer.parseInt(input[1]) - 1;

                union(a, b);
            }

            for (int i = 0; i < n; i++) {
                if (arr[i] < 0) answer++;
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new int[n];
        Arrays.fill(arr, -1);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        int heightA = Math.abs(rootA);
        int heightB = Math.abs(rootB);

        if (heightA > heightB) {
            arr[rootB] = rootA;
        } else if (heightA == heightB) {
            arr[rootB] = rootA;
            arr[rootA]--;
        } else {
            arr[rootA] = rootB;
        }
    }

    public static int find(int a) {
        while (arr[a] >= 0) {
            a = arr[a];
        }

        return a;
    }
}
