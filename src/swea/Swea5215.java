package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea5215 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n, maxCalorie;

    static int[] scores;
    static int[] calories;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            backtracking(0, 0, -1, 0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        maxCalorie = Integer.parseInt(input[1]);

        scores = new int[n];
        calories = new int[n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            scores[i] = Integer.parseInt(input[0]);
            calories[i] = Integer.parseInt(input[1]);
        }
    }

    public static void backtracking(int score, int calorie, int prevFood, int depth) {
        if (calorie > maxCalorie) {
            //prevFood를 제외한 점수를 저장
            answer = Math.max(answer, score - scores[prevFood]);
            return;
        }

        if (prevFood == n - 1) {
            //점수 저장
            answer = Math.max(answer, score);
            return;
        }

        for (int curFood = prevFood + 1; curFood < n; curFood++) {
            int nextScore = score + scores[curFood];
            int nextCalorie = calorie + calories[curFood];
            backtracking(nextScore, nextCalorie, curFood, depth + 1);
        }
    }
}
