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

        for (int i = 0; i < n; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
    }

    public static int slidingWindow() {
        int answer = 0;
        map.put(c, 1); //쿠폰 초밥 먹음
        for (int i = 0; i < k; i++) {
            if (map.containsKey(dishes[i])) {
                map.put(dishes[i], map.get(dishes[i]) + 1);
            } else {
                map.put(dishes[i], 1);
            }
        }

        int en = k - 1;
        for (int st = 0; st < n; st++) {
            answer = Math.max(answer, map.size());

            if (map.get(dishes[st]) > 1) {
                map.put(dishes[st], map.get(dishes[st]) - 1);
            } else {
                map.remove(dishes[st]);
            }

            en = (en + 1) % n;

            if (map.containsKey(dishes[en])) {
                map.put(dishes[en], map.get(dishes[en]) + 1);
            } else {
                map.put(dishes[en], 1);
            }
        }

        return answer;
    }

}
