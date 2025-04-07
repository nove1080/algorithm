package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj12865 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static Thing[] things;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        things = new Thing[n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value  = Integer.parseInt(input[1]);

            things[i] = new Thing(weight, value);
        }
    }
    
    static int solve() {
        int[][] bag = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                if(things[i - 1].weight > j) { //배낭에 넣을 수 없는 경우
                    bag[i][j] = bag[i - 1][j];
                } else { //배낭에 넣을 수 있는 경우
                    bag[i][j] = Math.max(bag[i - 1][j], bag[i - 1][j - things[i - 1].weight] + things[i - 1].value);
                }
            }
        }

        return bag[n][k];
    }

    static class Thing {
        int weight;
        int value;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
