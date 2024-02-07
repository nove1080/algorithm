//수 묶기
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1744 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static List<Integer> negative = new ArrayList<>();
    static List<Integer> positive = new ArrayList<>();
    static boolean zero;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num < 0) {
                negative.add(num);
            } else if (num > 0) {
                positive.add(num);
            } else {
                zero = true;
            }
        }

        Collections.sort(negative);
        Collections.sort(positive, Collections.reverseOrder());
    }

    static int solve() {
        int result = 0;
        Iterator<Integer> it = negative.iterator();
        while (it.hasNext()) {
            Integer now = it.next();
            if (!it.hasNext()) {
                if (!zero) {
                    result += now;
                }
                break;
            }
            Integer next = it.next();
            result += now * next;
        }

        Iterator<Integer> it2 = positive.iterator();
        while (it2.hasNext()) {
            Integer now = it2.next();
            if (!it2.hasNext()) {
                result += now;
                break;
            }
            Integer next = it2.next();
            if (now == 1 || next == 1) {
                result += now + next;
                continue;
            }
            result += now * next;
        }
        return result;
    }
}
