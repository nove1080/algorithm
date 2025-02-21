package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2023 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;

    public static void main(String[] args) throws Exception {
        init();
        searchAmazingPrimeNum(0, 0);
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
    }

    public static void searchAmazingPrimeNum(int num, int length) {
        if (length == n) {
            sb.append(num).append("\n");
            return;
        }
        
        for (int i = 1; i < 10; i++) {
            int nextNum = (num * 10) + i;
            if (isPrimeNum(nextNum)) {
                searchAmazingPrimeNum(nextNum, length + 1);
            }
        }
    }

    public static boolean isPrimeNum(int num) {
        if (num == 0 || num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        double sqrt = Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
