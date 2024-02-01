//12865 - 평범한 배낭

/**
 * DP
 * - 가방 무게를 기준으로 큰 무게를 작은 무게로 쪼개어 부분 문제로 만들 수 있음
 * https://c4u-rdav.tistory.com/58
 */
package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int maxWeight;
    static Item[] items;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        init();
        load();
//        printDP();
        System.out.println(dp[n - 1][maxWeight]);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        items = new Item[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            items[i] = new Item(weight, price);
        }

        dp = new int[n][maxWeight + 1];

        for (int i = 1; i < maxWeight + 1; i++) {
            if (items[0].weight > i) {
                continue;
            }
            dp[0][i] = items[0].price;
        }
    }

    static void load() {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < maxWeight + 1; j++) {
                if (j < items[i].weight) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i].weight] + items[i].price);
                }
            }
        }
    }

    static void printDP() {
        System.out.println("==================");
        for (int[] ints : dp) {
            System.out.println();
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
        }
        System.out.println();
        System.out.println("==================");

    }

    static class Item {
        public int weight;
        public int price;

        public Item(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}