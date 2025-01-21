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
            isPalindrome(cArr, 0, cArr.length - 1, 0);
        }
    }

    public static void isPalindrome(char[] cArr, int left, int right, int mismatchCount) {
        if (mismatchCount > 1 || left > right) {
            sb.append(mismatchCount).append("\n");
            return;
        }

        while (left <= right) {
            if (cArr[left] == cArr[right]) {
                left++;
                right--;
                continue;
            }

            //한쪽씩 지우고 비교해보기
            int tmpLeft = left + 1;
            if (cArr[tmpLeft] == cArr[right] && mismatchCount == 0) {
                left = tmpLeft;
                left++;
                right--;
                isPalindrome(cArr, left, right, mismatchCount + 1);
            }

            int tmpRight = right - 1;
            if (cArr[left] == cArr[tmpRight] && mismatchCount == 0) {
                left++;
                right = tmpRight;
                right--;
                isPalindrome(cArr, left, right, mismatchCount + 1);
            }
            
            //무한 루프
        }

        if (mismatchCount == 0) {
            sb.append(mismatchCount).append("\n");
        }
        return;
    }

}
