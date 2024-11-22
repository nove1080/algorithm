package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1644 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static List<Integer> sosu = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        initSosu();

        System.out.println(solve());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
    }

    private static void initSosu() {
        for (int i = 2; i <= n; i++) {
            double sqrt = Math.sqrt(i);
            boolean isSosu = true;
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0) {
                    isSosu = false;
                    break;
                }
            }
            if (isSosu) {
                sosu.add(i);
            }
        }
    }

    public static int solve() {
        //소수 배열 순회(투 포인터)
        int answer = 0;
        int en = 0;
        int sum = 0;

        for (int st = 0; st < sosu.size(); st++) {
            while (en < sosu.size() && sum < n) {
                sum += sosu.get(en);
                en++;
            }
            if (sum == n) {
                answer++;
            }
            sum -= sosu.get(st);
        }

        return answer;
    }
}
