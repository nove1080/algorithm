package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1107 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int target;
    static boolean[] isBroken;
    static PriorityQueue<Channel> channels;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(channels.poll().cost);
    }

    public static void init() throws Exception {
        target = Integer.parseInt(br.readLine());
        int broken = Integer.parseInt(br.readLine());

        isBroken = new boolean[10];
        if (broken > 0) {
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < broken; i++) {
                int brokenNumber = Integer.parseInt(input[i]);
                isBroken[brokenNumber] = true;
            }
        }

        channels = new PriorityQueue<>((c1, c2) -> c1.cost - c2.cost);
        channels.add(new Channel(100, Math.abs(target - 100)));
        for (int num = 0; num <= 1_000_000; num++) {
            if (num == 100) continue;
            if (isPossible(num)) {
                int length = String.valueOf(num).length();
                int distance = Math.abs(target - num);

                channels.add(new Channel(num, length + distance));
            }
        }
    }

    static boolean isPossible(int num) {
        char[] cArr = String.valueOf(num).toCharArray();
        for (char c : cArr) {
            if (isBroken[c - '0']) return false;
        }

        return true;
    }

    static class Channel {
        int num;
        int cost;

        public Channel(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Channel{" +
                "num=" + num +
                ", cost=" + cost +
                '}';
        }
    }
}

