package greedy;

import java.io.*;
import java.util.*;

public class Boj1202 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;
    static long totalValue;
    static PriorityQueue<Jewel> jewels = new PriorityQueue<>((j1, j2) -> j1.weight - j2.weight); //무게 오름차순
    static PriorityQueue<Jewel> tempBag = new PriorityQueue<>((j1, j2) -> j2.value - j1.value); //가격 내림차순
    static ArrayList<Integer> bags;

    public static void main(String[] args) throws Exception {
        init();
        for (Integer bag : bags) {
            while (canAdd(bag)) {
                tempBag.add(jewels.poll());
            }
            if (!tempBag.isEmpty()) {
                totalValue += tempBag.poll().value;
            }
        }
        System.out.println(totalValue);
    }

    private static boolean canAdd(Integer bag) {
        return !jewels.isEmpty() && bag >= jewels.peek().weight;
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);

            jewels.add(new Jewel(weight, value));
        }

        bags = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);
    }

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}

