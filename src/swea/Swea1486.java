package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea1486 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;
    static int shelf;

    static int[] people;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            for (int i = 0; i <= n; i++) {
                comb(-1, i, 0);
            }
            sb.append("#").append(t).append(" ").append(Math.abs(answer - shelf)).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = Integer.MAX_VALUE;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        shelf = Integer.parseInt(input[1]);

        people = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(input[i]);
        }
    }

    public static void comb(int start, int r, int height) {
        if(r == 0) {
            if(height >= shelf) {
                answer = Math.min(height, answer);
            }
            return;
        }

        for (int next = start + 1; next < n; next++) {
            comb(next, r - 1, height + people[next]);
        }
    }
}
