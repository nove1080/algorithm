package binarysearch;

import java.io.*;
import java.util.*;

public class Boj13397 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(binarySearch());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }

    private static int binarySearch() {
        int st = 0;
        int en = Arrays.stream(arr).max().getAsInt();

        while (st < en) {
            int mid = (st + en) / 2;

            if (divideSection(mid) > m) st = mid + 1;
            else en = mid;
        }

        return en;
    }

    private static int divideSection(int maxScore) {
        int sectionSize = 1;
        int minValue = 10000;
        int maxValue = 1;
        int score = 0;

        for (int i = 0; i < n; i++) {
            //1. 최대 혹은 최소라면 갱신한다.
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);

            //2. 점수 계산
            score = maxValue - minValue;

            //3. 점수를 초과한 경우 섹션을 끊는다.
            if (maxScore < score) {
                sectionSize++;
                //4. 최대, 최소값을 본인으로 갱신한다.
                minValue = arr[i];
                maxValue = arr[i];
                score = 0;
            }
        }
        return sectionSize;
    }
}

