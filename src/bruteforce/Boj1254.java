//팰린드롬 만들기
package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1254 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str;

    public static void main(String[] args) throws Exception {
        init();

        int minimumPalindromeLength = str.length();
        int cutSize = 0;
        while(!isPalindrome(str)) {
            str = str.substring(1);
            cutSize++;
        }

        minimumPalindromeLength += cutSize;
        System.out.println(minimumPalindromeLength);
    }

    public static void init() throws Exception {
        str = br.readLine();
    }

    public static boolean isPalindrome(String s) {

        int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) return false;

            start++;
            end--;
        }

        return true;
    }

}
