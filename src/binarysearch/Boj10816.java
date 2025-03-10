//숫자 카드 2
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10816 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[] source;
    static int[] targets;

    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(source);
        for (int target : targets) {
            sb.append(search(target)).append(" ");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        source = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            source[i] = Integer.parseInt(input[i]);
        }

        m = Integer.parseInt(br.readLine());
        targets = new int[m];
        input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            targets[i] = Integer.parseInt(input[i]);
        }
    }

    public static int search(int target) {
        return upperBound(target) - lowerBound(target);
    }

    public static int lowerBound(int target) {
        int st = 0;
        int en = n;

        while (st < en) {
            int mid = (st + en) / 2;

            if(source[mid] < target) st = mid + 1;
            if(source[mid] >= target) en = mid;
        }

        return en;
    }

    public static int upperBound(int target) {
        int st = 0;
        int en = n;

        while (st < en) {
            int mid = (st + en) / 2;

            if (source[mid] <= target) st = mid + 1;
            if (source[mid] > target) en = mid;
        }

        return en;
    }
}
