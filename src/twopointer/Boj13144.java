package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj13144 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;
    static int[] index;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        index = new int[100005];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.fill(index, -1);
    }

    public static long solve() {
        long result = 0;
        int lastHighestSt = 0;
        int st = 0;
        for (int end = 0; end < arr.length; end++) {
            int curNum = arr[end];
            if (index[curNum] != -1 && index[curNum] != end) {
                if (lastHighestSt > index[curNum] + 1) {
                    st = lastHighestSt;
                } else {
                    st = index[curNum] + 1;
                }
                lastHighestSt = Math.max(st, lastHighestSt);
            }
            index[curNum] = end;
            result += end - st + 1;
        }

        return result;
    }

}
