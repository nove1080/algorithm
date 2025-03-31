//좋은수열
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2661 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static String answer = "";

    public static void main(String[] args) throws Exception {
        init();
        backtracking(new StringBuilder("1"), 1, 1);
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
    }

    public static void backtracking(StringBuilder seq, int prev, int depth) {
        //답이 저장되었는가?
        if (!answer.isEmpty()) return;

        //좋은 수열인가?
        for (int i = 1; i <= seq.length() / 2; i++) {
            String back = seq.substring(depth - i, depth);
            String front = seq.substring(depth - i - i, depth - i);

            if (isBadSequence(back, front)) return;
        }

        if (depth == n) {
            answer = seq.toString();
            return;
        }

        for (int cur = 1; cur < 4; cur++) {
            if (cur == prev) continue;
            backtracking(seq.append(cur), cur, depth + 1);
            seq.deleteCharAt(depth);
        }
    }

    private static boolean isBadSequence(String back, String front) {
        return back.equals(front);
    }

}
