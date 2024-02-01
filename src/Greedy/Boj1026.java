//보물
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj1026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Integer[] a;
    static int[] b;
    static int answer;
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        a = new Integer[n];
        b = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solve() {
        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(b);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i] * b[i];
        }

        return sum;
    }
}
