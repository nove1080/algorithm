//파도반 수열

/**
 * 직접 해보면 규칙성을 찾을 수 있음
 */
package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9461 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int t;
    static int[] testcase;
    static long[] padoban;

    public static void main(String[] args) throws Exception {
        init();
        for (int tc : testcase) {
            sb.append(padoban[tc - 1]).append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        t = Integer.parseInt(br.readLine());
        testcase = new int[t];
        for (int i = 0; i < t; i++) {
            testcase[i] = Integer.parseInt(br.readLine());
        }

        initPadoban();
    }

    static void initPadoban() {
        padoban = new long[100];
        padoban[0] = 1;
        padoban[1] = 1;
        padoban[2] = 1;
        padoban[3] = 2;
        padoban[4] = 2;

        for(int i = 5; i < 100; i++) {
            padoban[i] = padoban[i - 5] + padoban[i - 1];
        }
    }
}
