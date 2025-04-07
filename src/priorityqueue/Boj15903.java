package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj15903 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;

    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        init();

        while (m-- > 0) {
            Long n1 = pq.poll();
            Long n2 = pq.poll();

            pq.add(n1 + n2);
            pq.add(n1 + n2);
        }
        System.out.println(pq.stream().mapToLong(n1 -> n1).sum());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(input[i]));
        }
    }
}
