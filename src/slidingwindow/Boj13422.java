package slidingwindow;

import java.io.*;
import java.util.*;

public class Boj13422 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int tc, n;
    static int target;
    static int limit;

    static int[] homes;

    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            init();

            int answer = 0;

            if (target == n) answer = searchNEqualM();
            if (target < n) answer = search();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        target = Integer.parseInt(input[1]);
        limit = Integer.parseInt(input[2]);

        homes = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            homes[i] = Integer.parseInt(input[i]);
        }
    }

    private static int search() {
        int sum = 0;
        int cnt = 0;

        //초기값
        for (int i = 0; i < target; i++) sum += homes[i];

        if (sum < limit) cnt++;

        for (int st = 1; st < n; st++) {
            int en = (st + target - 1) % n;

            sum -= homes[st - 1];
            sum += homes[en];

            if (sum < limit) cnt++;
        }

        return cnt;
    }

    private static int searchNEqualM() {
        int money = 0;
        for (int i = 0; i < target; i++) {
            money += homes[i];
        }

        if (money < limit) return 1;
        return 0;
    }
}

