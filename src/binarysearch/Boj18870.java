//좌표 압축
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Boj18870 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] origin;
    static int[] sortedArr;
    static HashMap<Integer, Integer> maps = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        init();
        compress();
        printResult();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        origin = new int[n];
        sortedArr = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            origin[i] = Integer.parseInt(input[i]);
            sortedArr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(sortedArr);
    }

    public static void compress() {
        int num = 0;

        int prev = Integer.MIN_VALUE;
        for (int cur : sortedArr) {
            if(prev != cur) {
                maps.put(cur, num);
                num++;
            }
            prev = cur;
        }
    }

    public static void printResult() {
        for (int num : origin) {
            sb.append(maps.get(num)).append(" ");
        }
        System.out.println(sb);
    }

}