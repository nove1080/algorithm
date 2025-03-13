package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1920 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static int[] arr1, arr2;

    public static void main(String[] args) throws Exception {
        init();
        Arrays.sort(arr1);
//        binarySearchUsingLibrary();
        for (int target : arr2) {
            sb.append(binarySearch(arr1, target) >= 0 ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        arr1 = new int[n];
        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        arr2 = new int[m];
        input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(input[i]);
        }
    }

    public static void binarySearchUsingLibrary() {
        for (int target : arr2) {
            sb.append(Arrays.binarySearch(arr1, target) >= 0 ? 1 : 0).append("\n");
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int st = 0;
        int en = arr.length - 1;

        while (st <= en) {
            int mid = (st + en) / 2;

            if(arr[mid] < target) {
                st = mid + 1;
            } else if(arr[mid] > target) {
                en = mid - 1;
            } else { //발견
                return mid;
            }
        }

        return -1;
    }
}