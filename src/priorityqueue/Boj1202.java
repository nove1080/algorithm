package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1202 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, k;

    static PriorityQueue<Jewel> pq = new PriorityQueue<>((j1, j2) -> j2.v - j1.v);

    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int m = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            pq.add(new Jewel(m, v));
        }

        for (int i = 0; i < k; i++) {

        }
    }

    static int simulate() {
        int answer = 0;
        while (pq.isEmpty() || k <= 0) {
            Jewel jewel = pq.poll();


        }

        return answer;
    }

    static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

}
