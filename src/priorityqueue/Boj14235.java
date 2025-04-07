package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj14235 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);

    public static void main(String[] args) throws Exception {
        init();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            if (a == 0) {
                sb.append(pq.isEmpty() ? -1 : pq.poll()).append("\n");
            }
            for (int j = 1; j <= a; j++) {
                pq.add(Integer.parseInt(input[j]));
            }
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
    }

}