package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2529 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n = 10;
    static int[] nums;
    static boolean[] picked = new boolean[n];

    static int size;
    static boolean[] inequalitySigns; //true : <, false : >

    static long maximumNumber = Long.MIN_VALUE;
    static long minimumNumber = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 0; i < n; i++) {
            picked[i] = true;
            nums[0] = i;
            solve(1, i);
            picked[i] = false;
        }

        System.out.println(String.valueOf(maximumNumber).length() < size + 1 ? ("0" + maximumNumber) : maximumNumber);
        System.out.println(String.valueOf(minimumNumber).length() < size + 1 ? ("0" + minimumNumber) : minimumNumber);
    }

    public static void init() throws Exception {
        size = Integer.parseInt(br.readLine());
        inequalitySigns = new boolean[size];
        nums = new int[size + 1];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            inequalitySigns[i] = input[i].charAt(0) == '<';
        }
    }

    public static void solve(int depth, int prev) {
        if(depth == size + 1) {
            long num = 0;
            long weight = 1;
            for (int i = 0; i < nums.length; i++) {
                num += nums[size - i] * weight;
                weight *= 10;
            }

            maximumNumber = Math.max(maximumNumber, num);
            minimumNumber = Math.min(minimumNumber, num);

            return;
        }

        if(inequalitySigns[depth - 1]) { //< : 더 큰 숫자 고르기
            for (int i = prev + 1; i < n; i++) {
                if (picked[i]) continue;

                picked[i] = true;
                nums[depth] = i;
                solve(depth + 1, i);
                picked[i] = false;
            }
        } else { //> : 더 작은 숫자 고르기
            for (int i = 0; i < prev; i++) {
                if (picked[i]) continue;

                picked[i] = true;
                nums[depth] = i;
                solve(depth + 1, i);
                picked[i] = false;
            }
        }
    }
}
