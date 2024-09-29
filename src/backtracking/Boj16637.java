//괄호 추가하기
//연산자에 우선순위를 표기
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj16637 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int answer = Integer.MIN_VALUE;
    static int n;
    static char[] operators;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        init();
        backtracking(0, nums[0]);
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        operators = new char[n/2];
        nums = new int[n / 2 + 1];

        int numsIdx = 0;
        int operatorsIdx = 0;
        for (int i = 0; i < n; i++) {
            if(i%2 == 0) {
                nums[numsIdx++] = input[i] - '0';
            } else {
                operators[operatorsIdx++] = input[i];
            }
        }
    }

    public static void backtracking(int idx, int result) {
        if (idx >= operators.length) {
            answer = Math.max(answer, result);
            return;
        }

        int nonBracketsResult = calc(result, operators[idx], nums[idx + 1]);
        backtracking(idx + 1, nonBracketsResult);

        if (idx < operators.length - 1) { //괄호가 가능하다면
            int bracketsResult = calc(result, operators[idx], calc(nums[idx + 1], operators[idx + 1], nums[idx + 2]));
            backtracking(idx + 2, bracketsResult);
        }
    }

    public static int calc(int num1, char oper, int num2) {
        switch (oper) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            default : throw new IllegalArgumentException();
        }
    }
}
