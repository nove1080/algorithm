package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj1715 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(simulate());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(br.readLine());
            pq.add(card);
        }
    }

    public static int simulate() {
        int answer = 0;
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            pq.add(sum);
            answer += sum;
        }

        return answer;
    }
}
