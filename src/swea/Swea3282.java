package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea3282 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n, k;

    static int[][] bag;
    static Thing[] things;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            answer = solve();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        bag = new int[n + 1][k + 1];
        things = new Thing[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            things[i] = new Thing(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
    }

    public static int solve() {
        //점화식 적용
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= k; l++) {
                if (things[i - 1].w > l) { // i 번째 물건을 넣을 수 없는 경우
                    bag[i][l] = bag[i - 1][l];
                } else { //i 번째 물건을 넣을 수 있는 경우
                    bag[i][l] = Math.max(bag[i - 1][l], bag[i - 1][l - things[i - 1].w] + things[i - 1].c); //물건을 넣은 경우, 물건을 넣지 않은 경우 중의 최댓값
                }
            }
        }

        return bag[n][k];
    }

    static class Thing {
        int w, c;

        public Thing(int w, int c) {
            this.w = w;
            this.c = c;
        }
    }
}
