package Hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20920 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static Map<String, Integer> map = new HashMap<>();
    static PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
        if (!map.get(s1).equals(map.get(s2))) {
            return map.get(s2) - map.get(s1);
        } else if (s1.length() != s2.length()) {
            return s2.length() - s1.length();
        } else {
            return s1.compareTo(s2);
        }
    });

    public static void main(String[] args) throws Exception {
        init();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (str.length() >= m) {
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                } else {
                    map.put(str, 0);
                    pq.add(str);
                }
            }
        }

//        for(String s : map.keySet()) {
//            pq.add(s);
//        }
    }
}
