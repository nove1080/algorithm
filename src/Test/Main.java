package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int num;
    static long goal;
    static int[] trees;
    static int maxTreeHeight = 0;

    public static void main(String[] args) throws Exception {
        init();
        getHeight(0, maxTreeHeight);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        goal = Long.parseLong(st.nextToken());

        trees = new int[num];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxTreeHeight = Math.max(maxTreeHeight, trees[i]);
        }
    }

    static long getResult(long height) {
        long sum = 0;
        for (int tree : trees) {
            if (tree > height) {
                sum += tree - height;
            }
        }

        return sum;
    }

    //이진 탐색
    static void getHeight(int start, int end) {
        int maxH = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            long result = getResult(mid);
            if (result >= goal) {
                start = mid + 1;
                maxH = Math.max(maxH, mid);
            } else {
                end = mid - 1;
            }
            System.out.println(maxH);
        }
    }
}