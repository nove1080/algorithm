package greedy;

import java.io.*;
import java.util.*;

public class Boj1339 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long sum;
    static int n;
    static HashMap<Character, Integer> alphaScore = new HashMap<>();

    public static void main(String[] args) throws Exception {
        init();

        List<Integer> orderedAlphaScore = new ArrayList(alphaScore.values());
        orderedAlphaScore.sort((i1, i2) -> i2 - i1);
        int num = 9;
        for (Integer i : orderedAlphaScore) {
            sum += i * num;
            num--;
        }

        System.out.println(sum);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            int weight = 1;
            for (int j = 0; j < input.length(); j++) {
                char alpha = input.charAt(input.length() - j - 1);
                int score = weight;

                if (alphaScore.containsKey(alpha)) {
                    score += alphaScore.get(alpha);
                }
                alphaScore.put(alpha, score);

                weight *= 10;
            }
        }
    }
}
