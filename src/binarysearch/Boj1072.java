package binarysearch;

import java.io.*;
import java.util.*;

public class Boj1072 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long x, y;
    static int z;
    static long answer;

    public static void main(String[] args) throws Exception {
        init();
        z = (int) winRate(0);
        if (z >= 99) {
            answer = -1;
        } else {
            //이분탐색
            int st = 1;
            int en = 2_000_000_000; //10억
            while (st < en) {
                int mid = (st + en) / 2;
                if (winRate(mid) < z + 1) {
                    st = mid + 1;
                } else {
                    en = mid;
                    answer = mid;
                }
            }
        }

        System.out.println(answer);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        x = Integer.parseInt(input[0]);
        y = Integer.parseInt(input[1]);
    }

    private static double winRate(int alpha) {
        return (y + alpha) * 100.0 / (x + alpha);
    }

}

