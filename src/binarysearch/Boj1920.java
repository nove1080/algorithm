//수 찾기
package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1920 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static int[] arr1;
    static int[] arr2;

    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(arr1);
        for (int i : arr2) {
            sb.append(binarySearch(0, n - 1, i)).append("\n");
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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int binarySearchWithRecursion(int start, int end, int key) {
        if(start <= end) {
            int mid = (start + end) / 2;
            if (arr1[mid] == key) {
                return 1;
            } else if (arr1[mid] > key) {
                return binarySearch(start, mid - 1, key);
            } else {
                return binarySearch(mid + 1, end, key);
            }
        }
        return 0;
    }


    static int binarySearch(int start, int end, int key) {
        while(start <= end) {
            int mid = (start + end) / 2;
            if(arr1[mid] > key) {
                end = mid - 1;
            } else if (arr1[mid] < key) {
                start = mid + 1;
            } else { //found
                return 1;
            }
        }
        return 0;
    }

}
