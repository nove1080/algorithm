package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10972 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] permutation;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(getNextPermutation());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = Integer.parseInt(input[i]);
        }
    }

    public static String getNextPermutation() {
        int after = permutation[n - 1];
        for (int i = 1; i < n; i++) {
            int cur = permutation[n - i - 1];

            if (cur < after) { //뒤에 오는 숫자가 더 큰 경우
                int targetIdx = n - i;
                int minValue = after;
                for (int j = 1; j < i; j++) {
                    if (permutation[n - i + j] > cur && minValue > permutation[n - i + j]) {
                        targetIdx = n - i + j;
                        minValue = permutation[n - i + j];
                    }
                }
                permutation[n - i - 1] = minValue;
                permutation[targetIdx] = cur;

                Arrays.sort(permutation, n - i, n);

                return arrayToString(permutation);
            }
            after = cur;
        }
        return "-1";
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }
}
