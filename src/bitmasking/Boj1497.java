package bitmasking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1497 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static int[][] board;

    static int minimumGitter = Integer.MAX_VALUE;
    static int maximumPlay = -1;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 1; i <= n; i++) {
            comb(n, i, 0, new boolean[n]);
        }
        if (minimumGitter == Integer.MAX_VALUE) {
            minimumGitter = -1;
        }
        System.out.println(minimumGitter);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            char[] musics = input[1].toCharArray();
            for (int j = 0; j < m; j++) {
                int mark = musics[j] == 'Y' ? 1 : 0;
                board[i][j] = mark;
            }
        }
    }

    public static void comb(int n, int r, int start, boolean[] arr) {
        if (r == 0) {
            save(arr);
            return;
        }

        for (int i = start; i < n; i++) {
            arr[i] = true;
            comb(n, r - 1, i + 1, arr);
            arr[i] = false;
        }
    }

    public static void save(boolean[] arr) {
        int play = 0;

        int totalGitter = 0;
        boolean[] vis = new boolean[m];
        for (int i = 0; i < n; i++) {
            if (arr[i]) {
                totalGitter++;
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && !vis[j]) {
                        play++;
                        vis[j] = true;
                    }
                }
            }
        }

        if (play != 0 && maximumPlay < play) {
            maximumPlay = play;
            minimumGitter = totalGitter;
        } else if (maximumPlay == play) {
            minimumGitter = Math.min(minimumGitter, totalGitter);
        }
    }
}
