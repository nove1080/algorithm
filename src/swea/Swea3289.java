package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Swea3289 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static StringBuilder answer;

    static int[] arr;

    static int n, m;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < m; i++) {
                String[] input = br.readLine().split(" ");
                switch (input[0]) {

                    case "0":
                        union(Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]) - 1);
                        break;
                    case "1":
                        int root1 = find(Integer.parseInt(input[1]) - 1);
                        int root2 = find(Integer.parseInt(input[2]) - 1);
                        answer.append(root1 == root2 ? 1 : 0);
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = new StringBuilder();

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new int[n];
        Arrays.fill(arr, -1);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        int heightRootA = Math.abs(arr[rootA]);
        int heightRootB = Math.abs(arr[rootB]);

        if (heightRootA > heightRootB) {
            arr[rootB] = rootA;
        }
        if (heightRootA == heightRootB) {
            arr[rootA] = rootB;
            arr[rootB]--;
        }
        if (heightRootA < heightRootB) {
            arr[rootA] = rootB;
        }
    }

    private static int find(int num) {
        while (arr[num] >= 0) {
            num = arr[num];
        }

        return num;
    }
}
