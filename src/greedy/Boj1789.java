package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1789 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long s;

    public static void main(String[] args) throws Exception {
        init();

        long sum = 0;
        int n = 0;
        int i = 1;
        while (sum < s) {
            sum += i;
            n++;
            i++;
        }

        System.out.println(sum > s ? n - 1 : n);
    }

    public static void init() throws Exception {
        s = Long.parseLong(br.readLine());
    }
}
