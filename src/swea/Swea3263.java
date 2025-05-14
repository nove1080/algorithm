package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea3263 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;
        n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {

        }
    }

}
