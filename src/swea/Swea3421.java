package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea3421 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static int m;

    static int[] prohibits; //prohibits[i] : 재료 i에 대해 함께 조합될 수 없는 재료의 bit 를 1로 표현

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            for (int i = 0; i <= n; i++) {
                backtracking(0, -1, i);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        prohibits = new int[n];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int ingredient1 = Integer.parseInt(input[0]) - 1;
            int ingredient2 = Integer.parseInt(input[1]) - 1;

            prohibits[ingredient1] |= 1 << ingredient2;
            prohibits[ingredient2] |= 1 << ingredient1;
        }
    }

    /**
     * chance 번 고를 수 있는 재료 조합 중 가능한 재료 조합 수를 저장한다.
     * @param ingredients 현재까지 고른 재료
     * @param prev 이전에 고른 재료
     * @param chance 남은 재료 선정 횟수
     */
    public static void backtracking(int ingredients, int prev, int chance) {
        if (prev >= 0 && isProhibitCombination(ingredients, prev)) {
            return;
        }

        if (chance == 0) {
            answer++;
            return;
        }

        for (int next = prev + 1; next < n; next++) {
            int nextIngredients = ingredients | (1 << next);
            backtracking(nextIngredients, next, chance - 1);
        }
    }

    /**
     * 이전에 선택한 재료에 대해서 금지되는 조합이 존재하는지 판단
     * @param ingredients 현재 재료 조합
     * @param prev 최근에 선정한 재료
     * @return 금지된 조합인 경우 true, 그 외 false
     */
    private static boolean isProhibitCombination(int ingredients, int prev) {
        return (ingredients & prohibits[prev]) != 0;
    }
}
