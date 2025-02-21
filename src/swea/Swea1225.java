package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea1225 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n = 8;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        int tc = 10;
        for (int t = 1; t <= tc; t++) {
            init();
            nums = compressNumber(nums);
            String password = generatePassword();

            sb.append("#").append(t).append(" ").append(password).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        br.readLine();
        answer = 0;

        String[] input = br.readLine().split(" ");
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
    }

    public static String generatePassword() {
        int idx = 0;
        int weight = 1;
        while (true) {
            nums[idx] -= weight;

            if(nums[idx] <= 0) {
                nums[idx] = 0;
                break;
            }

            idx = (idx + 1) % n;
            weight = weight % 5 + 1;
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < n; i++) {
            password.append(nums[(idx + i + 1) % n]).append(" ");
        }

        return password.toString();
    }

    /**
     * 각 자리 수를 15로 나누어 수의 크기를 압축하여 반환한다.
     */
    public static int[] compressNumber(int[] target) {
        int[][] data = new int[n][2]; //[i][0] : i번째 수의 몫, [i][1] : i번째 수의 나머지

        int minCycle = 0;
        for (int i = 0; i < n; i++) {
            data[i][0] = target[i] / 15;
            data[i][1] = target[i] % 15;

            minCycle = Math.min(minCycle, data[i][0]);
        }

        int[] compressedResult = new int[n];
        for (int i = 0; i < n; i++) {
            if (data[i][0] > minCycle) {
                data[i][1] += 15 * (data[i][0] - minCycle);
            }
            compressedResult[i] = data[i][1];
        }

        return compressedResult;
    }
}
