package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea6808 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n = 9;
    static int[] gyuyeung;

    static int[] others; //인영이가 사용가능한 카드 모음
    static int[] picked; //선택된 카드 (순서대로)
    static boolean[] vis; //이미 고른 카드

    static int win, lose;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            for (int i = 0; i < n; i++) {
                picked[0] = others[i];
                vis[i] = true;
                solve(1);
                vis[i] = false;
            }

            sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        win = 0; lose = 0;
        picked = new int[n];
        vis = new boolean[n];

        String[] input = br.readLine().split(" ");

        boolean[] temp = new boolean[n * 2 + 1]; //1-indexed, true: 규영이가 고른 카드
        gyuyeung = new int[n];
        for (int i = 0; i < n; i++) {
            gyuyeung[i] = Integer.parseInt(input[i]);
            temp[gyuyeung[i]] = true;
        }

        int idx = 0;
        others = new int[n];
        for (int i = 1; i <= n * 2; i++) {
            if (!temp[i]) others[idx++] = i;
        }
    }

    public static void solve(int depth) {
        if(depth == n) {
            //승패 결정
            int gyuyeungScore = 0;
            int inyeungScore = 0;
            for (int i = 0; i < n; i++) {
                if(picked[i] < gyuyeung[i]) {
                    gyuyeungScore += picked[i] + gyuyeung[i];
                } else if (picked[i] > gyuyeung[i]) {
                    inyeungScore += picked[i] + gyuyeung[i];
                }
            }

            if(gyuyeungScore > inyeungScore) win++;
            else if (gyuyeungScore < inyeungScore) lose++;
            return;
        }

        for (int next = 0; next < n; next++) {
            if(vis[next]) continue;

            picked[depth] = others[next];
            vis[next] = true;
            solve(depth + 1);
            vis[next] = false;
        }

    }
}
