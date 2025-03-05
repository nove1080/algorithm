package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea4008 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int maxResult;
    static int minResult;

    static int n;

    static int[] operations; // +, -, *, /
    static int[] nums;

    static int count;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            backtracking(1, nums[0]);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
        System.out.println("RECURSION COUNT = " + count);
    }

    public static void init() throws Exception {
        answer = 0;
        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;

        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        operations = new int[4];
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
    }

    public static void backtracking(int depth, int result) {
        count++;
        if (depth == n) { //수식이 완성
            minResult = Math.min(minResult, result);
            maxResult = Math.max(maxResult, result);
            answer = maxResult - minResult;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i]--;

                int nextResult = result;
                if (i == 0) nextResult += nums[depth];
                else if (i == 1) nextResult -= nums[depth];
                else if (i == 2) nextResult *= nums[depth];
                else nextResult /= nums[depth];

                backtracking(depth + 1, nextResult);

                operations[i]++;
            }
        }
    }

}
