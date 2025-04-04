package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1162 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m, k;

    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int c1 = Integer.parseInt(input[0]);
            int c2 = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);


        }
    }

}
