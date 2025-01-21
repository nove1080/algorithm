import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17609 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb);
    }

    public static void init() throws Exception {
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            char[] cArr = br.readLine().toCharArray();
            sb.append(isPalindrome(cArr, 0, cArr.length - 1, 0)).append("\n");
        }
    }

    public static int isPalindrome(char[] cArr, int left, int right, int mismatchCount) {
        if(mismatchCount >= 2) return 2;

        while (left <= right) {
            if (cArr[left] == cArr[right]) { //일치한다면
                left++;
                right--;
            } else { //불일치한다면
                return Math.min(isPalindrome(cArr, left + 1, right, mismatchCount + 1),
                    isPalindrome(cArr, left, right - 1, mismatchCount + 1));
            }
        }
        return mismatchCount;
    }

}
