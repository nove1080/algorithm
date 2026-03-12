package binarysearch;

import java.io.*;
import java.util.*;

public class Boj3020 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, h;
    static int[] top, down;
    static HashMap<Integer, Integer> sections = new HashMap();

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 1; i <= h; i++) {
            int conflict = 0;
            conflict += getConflict(i, down);
            conflict += getConflict(h - i + 1, top);

            if (sections.containsKey(conflict)) {
                sections.put(conflict, sections.get(conflict) + 1);
            } else {
                sections.put(conflict, 1);
            }
        }

        ArrayList<Integer> keys = new ArrayList<>(sections.keySet());
        keys.sort((k1, k2) -> k1 - k2);
        System.out.println(keys.get(0) + " " + sections.get(keys.get(0)));
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        h = Integer.parseInt(input[1]);

        top = new int[n / 2];
        down = new int[n / 2];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                down[i / 2] = Integer.parseInt(br.readLine());
            } else {
                top[(i - 1) / 2] = Integer.parseInt(br.readLine());
            }
        }

        Arrays.sort(top);
        Arrays.sort(down);
    }

    private static int getConflict(int target, int[] arr) {
        return arr.length - lowerBound(target, arr);
    }

    private static int lowerBound(int target, int[] arr) {
        int st = 0;
        int en = n / 2;

        while (st < en) {
            int mid = (st + en) / 2;

            if (arr[mid] < target) st = mid + 1;
            else en = mid;
        }

        return en;
    }
}


