//회전 초밥

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Boj15961 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n; //접시의 수
    static int d; //초밥의 가짓수
    static int k; //연속해서 먹는 접시의 수
    static int c; //쿠폰 번호

    static int[] dishes;
    static int[] sushi;

    static HashMap<Integer, Integer> map = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(slidingWindow());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        c = Integer.parseInt(input[3]);

        dishes = new int[n];
        sushi = new int[d + 1];

        for (int i = 0; i < n; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
    }

    public static int slidingWindow() {
        int answer = 0;

        //쿠폰 초밥 먹기
        sushi[c]++;
        answer++;

        for (int i = 0; i < k; i++) {
            if(sushi[dishes[i]]++ == 0) answer++;
        }

        int result = answer;
        int en = k - 1;
        for (int st = 0; st < n; st++) {
            answer = Math.max(answer, result);
            if (sushi[dishes[st]]-- == 1) result--;

            en = (en + 1) % n;
            if (sushi[dishes[en]]++ == 0) result++;
        }

        return answer;
    }

}
