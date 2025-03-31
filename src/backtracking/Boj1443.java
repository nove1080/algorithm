package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1443 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int d, p;

    static long answer;

    public static void main(String[] args) throws Exception {
        init();

        backtracking(1L, 0, 9);
        System.out.println(answer == 0 ? -1 : answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        d = Integer.parseInt(input[0]);
        p = Integer.parseInt(input[1]);

    }

    public static void backtracking(Long result, int depth, int max) {
        if (result.toString().length() > d) return;

//        if (answer != 0) return;
        if (depth == p) {
            answer = Math.max(answer, result);
            return;
        }

        for (int i = max; i > 1; i--) {
            backtracking(result * i, depth + 1, i);
        }
    }
}
