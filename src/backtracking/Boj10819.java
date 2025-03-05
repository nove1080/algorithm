package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10819 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] nums;
    static boolean[] vis;
    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n; i++) {
            vis[i] = true;
            backtracking(1, 0, nums[i]);
            vis[i] = false;
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        nums = new int[n];
        vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
    }

    public static void backtracking(int depth, int sumOfDiff, int num) {
        if(depth == n) {
            answer = Math.max(answer, sumOfDiff);
        }

        for (int i = 0; i < n; i++) {
            if(vis[i]) continue;

            vis[i] = true;
            int diff = Math.abs(num - nums[i]);
            backtracking(depth + 1, sumOfDiff + diff, nums[i]);
            vis[i] = false;
        }
    }

}
