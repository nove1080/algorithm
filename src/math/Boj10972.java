package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10972 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        init();
//        System.out.println(getNextPermutation());
        System.out.println(getNextPermutationV2());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
    }

//    public static String getNextPermutation() {
//        int after = nums[n - 1];
//        for (int i = 1; i < n; i++) {
//            int cur = nums[n - i - 1];
//
//            if (cur < after) { //뒤에 오는 숫자가 더 큰 경우
//                int targetIdx = n - i;
//                int minValue = after;
//                for (int j = 1; j < i; j++) {
//                    if (nums[n - i + j] > cur && minValue > nums[n - i + j]) {
//                        targetIdx = n - i + j;
//                        minValue = nums[n - i + j];
//                    }
//                }
//                nums[n - i - 1] = minValue;
//                nums[targetIdx] = cur;
//
//                Arrays.sort(nums, n - i, n);
//
//                return arrayToString(nums);
//            }
//            after = cur;
//        }
//        return "-1";
//    }

    public static String getNextPermutationV2() {
        int idx1 = n - 1;
        while (idx1 > 0 && nums[idx1 - 1] > nums[idx1]) { //오름차순인 경우
            idx1--;
        }

        if(idx1 == 0) return "-1";

        int idx2 = n - 1;
        while (idx2 > 0 && nums[idx2] <= nums[idx1 - 1]) { //swap 대상 인덱스 선정하기
            idx2--;
        }

        swap(idx1 - 1, idx2);

        Arrays.sort(nums, idx1, n);
        return arrayToString(nums);
    }

    public static void swap(int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }

        return sb.toString();
    }
}
