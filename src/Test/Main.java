//12865 - 평범한 배낭

/**
 * DP
 * - 가방 무게를 기준으로 큰 무게를 작은 무게로 쪼개어 부분 문제로 만들 수 있음
 * - 가방 무게를 k라고 할 때, k-1 가방의 가치와 비교하며 갱신 여부를 결정(물건을 다 담아볼 것이다.)
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
    static int[] dp;

    public static void main(String[] args) throws Exception {
        init();
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            items[i] = new Item(weight, price);
        }

        Arrays.sort(items, (i1, i2) -> {
            return i1.weight - i2.weight;
        });

        dp = new int[n];
        if (items[0].weight > maxWeight) {
            dp[0] = 0;
        } else {
            dp[0] = items[0].price;
        }
    }

    static void load() {
        for (int i = 0; i < n; i++) {
            if (items[i].weight > maxWeight) {
                break;
            }
        }
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
