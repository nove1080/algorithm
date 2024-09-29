//숫자 카드 2
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10816 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static int[] arr1, arr2;
    static int[] counts;
    static int[] checks = new int[20000001];

    public static void main(String[] args) throws Exception {
        init();
//        for (int i = 0; i < m; i++) {
//            counts[i] = binarySearch(0, m - 1, arr2[i]);
//        }
        printAnswer();
    }

    private static void printAnswer() {
        for (int count : counts) {
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        counts = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
    }

//    static int binarySearch(int start, int end, int key) {
//        int count = 0;
//        while (start <= end) {
//            int mid = (start + end) / 2;
//
//            if (arr1[mid] < key) {
//                start = mid + 1;
//            } else if (arr1[mid] > key) {
//                end = mid - 1;
//            } else { //found
//                if(checks[key + 10000000] != 0) {
//                    count = checks[key + 10000000];
//                } else {
//                    for (int i = mid + 1; i < n; i++) {
//                        if(arr1[i] != key) break;
//                        count++;
//                    }
//                    for (int i = mid; i >= 0; i--) {
//                        if(arr1[i] != key) break;
//                        count++;
//                    }
//                    checks[key + 10000000] = count;
//                }
//                return count;
//            }
//        }
//
//        return 0;
//    }


}
