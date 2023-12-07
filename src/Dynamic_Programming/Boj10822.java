//1로 만들기
package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10822 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int num;
    static int[] exp = new int[1000005];

    public static void main(String[] args) throws Exception {
        init();
        dynamic();
        System.out.println(exp[num]);
    }

    static void init() throws IOException {
        num = Integer.parseInt(br.readLine());
    }

    static void dynamic() {
        for (int i = 2; i <= num; i++) {
            exp[i] = exp[i - 1] + 1;
            if (i % 2 == 0) {
                exp[i] = Math.min(exp[i], exp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                exp[i] = Math.min(exp[i], exp[i / 3] + 1);
            }
        }
    }
}
