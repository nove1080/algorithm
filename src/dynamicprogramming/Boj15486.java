package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj15486 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int retireDate;

    static int[] dp;

    /**
     * index: 일
     * value: 해당 일에 끝나는 상담의 리스트
     */
    static ArrayList<Task>[] schedule;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    static void init() throws Exception {
        retireDate = Integer.parseInt(br.readLine());


        schedule = new ArrayList[retireDate + 1];
        for (int i = 0; i < retireDate + 1; i++) {
            schedule[i] = new ArrayList<>();
        }

        for (int day = 0; day < retireDate; day++) {
            String[] input = br.readLine().split(" ");

            int period = Integer.parseInt(input[0]);
            int revenue = Integer.parseInt(input[1]);

            if (day + period > retireDate) continue;

            schedule[day + period].add(new Task(period, revenue));
        }

        dp = new int[retireDate + 1];
    }

    static int solve() {
        for (int day = 1; day < retireDate + 1; day++) {
            dp[day] = dp[day - 1];
            for (Task task : schedule[day]) {
                dp[day] = Math.max(dp[day - task.period] + task.revenue, dp[day]);
            }
        }

        return dp[retireDate];
    }

    static class Task {
        int period;
        int revenue;

        public Task(int period, int revenue) {
            this.period = period;
            this.revenue = revenue;
        }
    }
}
