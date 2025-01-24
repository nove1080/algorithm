//교환
package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1039 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String numStr;
    static boolean[][] vis;
    static int answer = Integer.MIN_VALUE;
    static int k;

    public static void main(String[] args) throws Exception {
        init();
        dfs(k);
        if (answer == Integer.MIN_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");

        numStr = input[0];
        k = Integer.parseInt(input[1]);
        vis = new boolean[k + 1][1000001];

    }

    /**
     *
     * @param cnt 변경 횟수
     */
    public static void dfs(int cnt) {
        if (vis[cnt][Integer.parseInt(numStr)]) return;
        vis[cnt][Integer.parseInt(numStr)] = true;

        if (numStr.charAt(0) == '0') return;

        if (cnt == 0) {
            answer = Math.max(Integer.parseInt(numStr), answer);
            return;
        }

        for (int i = 0; i < numStr.length(); i++) {
            for (int j = i + 1; j < numStr.length(); j++) {
                numStr = swap(i, j);
                dfs(cnt - 1);
                numStr = swap(j, i);
            }
        }
    }

    public static String swap(int i1, int i2) {
        char[] charArray = numStr.toCharArray();

        char tmp = charArray[i1];
        charArray[i1] = charArray[i2];
        charArray[i2] = tmp;

        return String.valueOf(charArray);
    }
}
