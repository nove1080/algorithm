package slidingwindow;//문자열 교환

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1522 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(slidingWindow(getALength(str)));
    }

    public static void init() throws Exception {
        str = br.readLine();
    }

    public static int getALength(String str) {
        int aLength = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                aLength++;
            }
        }

        return aLength;
    }

    public static int slidingWindow(int length) {
        int minimumExchange = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == 'b') minimumExchange++;
        }

        int en = length;
        int tmpResult = minimumExchange;
        for (int st = 0; st < str.length(); st++, en++) {
            minimumExchange = Math.min(minimumExchange, tmpResult);

            if (str.charAt(st) == 'b') tmpResult--;
            if (str.charAt(en % str.length()) == 'b') tmpResult++;
        }

        return minimumExchange;
    }
}
