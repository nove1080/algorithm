//나는야 포켓몬 마스터 이다솜
package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Boj1620 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int problem;
    static String[] names;
    static LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
    public static void main(String[] args) throws Exception {
        init();
        solve(problem);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        problem = Integer.parseInt(input[1]);
        names = new String[n];

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            names[i] = name;
            hashMap.put(name, i + 1);
        }
    }

    public static void solve(int problem) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < problem; i++) {
            String nameOrNum = br.readLine();

            try {
                int num = Integer.parseInt(nameOrNum);
                sb.append(names[num - 1]).append("\n");
            } catch (Exception e) {
                sb.append(hashMap.get(nameOrNum)).append("\n");
            }
        }

        System.out.println(sb);
    }

}
