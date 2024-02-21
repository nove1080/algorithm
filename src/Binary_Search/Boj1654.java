//1654: 랜선 자르기
package Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ownCables, reqCables;
    static int[] cables;
    static long maxHeight;

    public static void main(String[] args) throws Exception {
        init();
        searchCableLength(1, maxHeight);
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        ownCables = Integer.parseInt(st.nextToken());
        reqCables = Integer.parseInt(st.nextToken());

        cables = new int[ownCables];

        long sum = 0;
        for (int i = 0; i < ownCables; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            sum += cables[i];
        }

        maxHeight = sum / reqCables;
    }

    static void searchCableLength(long start, long end) {
        long result = 0;
        while (start <= end) {
            long height = (start + end) / 2;
            long quantity = 0;
            for (int i = 0; i < cables.length; i++) {
                quantity += cables[i] / height;
            }

            if (quantity < reqCables) {
                end = height - 1;
            } else {
                start = height + 1;
                result = Math.max(height, result);
            }
        }

        System.out.println(result);
    }
}
