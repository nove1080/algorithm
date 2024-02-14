//회장 뽑기
// bfs: depth가 점수가 된다.
package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2660 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] adj;
    static int[][] score;
    static boolean[] vis;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n; i++) {
            vis = new boolean[n];
            bfs(i, i, 1);
        }

        System.out.println("score============");
        for (int[] ints : score) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("answer===========");
        printAnswer();

    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        adj = new int[n][n];
        score = new int[n][n];
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken()) - 1;
            int num2 = Integer.parseInt(st.nextToken()) - 1;
            if(num1 == -2 && num2 == -2) break;

            adj[num1][num2] = 1;
            adj[num2][num1] = 1;
        }
    }
    static void bfs(int origin, int v, int depth) {
        vis[v] = true;

        Queue<Integer> q = new LinkedList<>();
        for (int next = 0; next < n; next++) {
            if(adj[v][next] == 0 || vis[next]) continue;
            vis[next] = true;
            score[origin][next] = depth;
            q.add(next);
        }
        while (!q.isEmpty()) {
            bfs(origin, q.poll(), depth + 1);
        }

    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        int[] finalScores = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                finalScores[i] = Math.max(score[i][j], finalScores[i]);
            }
        }

        int min = 99;
        int count = 0;
        for (int finalScore : finalScores) {
            if(finalScore == 0) continue;
            if(min > finalScore) {
                count = 0;
                min = finalScore;
            }
            if (min == finalScore) {
                count++;
            }
        }
        sb.append(min).append(" ").append(count).append("\n");

        for (int i = 0; i < n; i++) {
            if(min == finalScores[i]) {
                sb.append(i + 1).append(" ");
            }
        }

        System.out.println(sb);
    }
}
