package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj12865 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static Thing[] things;

    public static void main(String[] args) throws Exception {
        init();
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

        Arrays.sort(things, (t1, t2) -> {
            if(t1.weight == t2.weight) {
                return t2.value - t1.value; //가치가 높은 순서대로 오름차순
            } else {
                return t1.weight - t2.weight; //가벼운 물건 순서대로 정렬
            }
        });
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
