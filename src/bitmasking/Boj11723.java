//집합
package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11723 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    public static void main(String[] args) throws Exception {
        init();
//        nonBitMasking();
        bitMasking();
        System.out.println(sb);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
    }

    private static void nonBitMasking() throws IOException {
        boolean[] vis = new boolean[21];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "add":
                    vis[Integer.parseInt(input[1])] = true;
                    break;
                case "remove":
                    vis[Integer.parseInt(input[1])] = false;
                    break;
                case "check":
                    int result = 0;
                    if (vis[Integer.parseInt(input[1])]) result = 1;
                    sb.append(result).append("\n");
                    break;
                case "toggle":
                    if(vis[Integer.parseInt(input[1])]) {
                        vis[Integer.parseInt(input[1])] = false;
                    } else {
                        vis[Integer.parseInt(input[1])] = true;
                    }
                    break;
                case "all":
                    for (int j = 0; j < 20; j++) {
                        vis[j + 1] = true;
                    }
                    break;
                case "empty":
                    for (int j = 0; j < 20; j++) {
                        vis[j + 1] = false;
                    }
                    break;
            }
        }
    }

    private static void bitMasking() throws Exception {
        int vis = 0;
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "add":
                    vis = vis | (1 << (Integer.parseInt(input[1]) - 1));
                    break;
                case "remove":
                    vis = vis & ~(1 << (Integer.parseInt(input[1]) - 1));
                    break;
                case "check":
                    int result = (vis >> (Integer.parseInt(input[1])) - 1) & 1;
                    sb.append(result).append("\n");
                    break;
                case "toggle":
                    vis = vis ^ (1 << Integer.parseInt(input[1]) - 1);
                    break;
                case "all":
                    vis = 0xffffffff;
                    break;
                case "empty":
                    vis = 0;
                    break;
            }
        }
    }

}
