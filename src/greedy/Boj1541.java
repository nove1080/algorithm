//잃어버린 괄호
/**
 * 1. '-' 부분을 기준으로 문자열을 파싱한다.
 * 2. 파싱된 문자열 내부를 합친다.
 * 3. num1 - num2 - ... - numN 계산한다.
 */
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1541 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        int result = 0;
        boolean isFirst = true;

        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        while (st.hasMoreTokens()) {
            int sum = 0;

            StringTokenizer addition = new StringTokenizer(st.nextToken(), "+");
            while (addition.hasMoreTokens()) {
                sum += Integer.parseInt(addition.nextToken());
            }

            if(isFirst) {
                result += sum;
                isFirst = false;
            } else {
                result -= sum;
            }
        }

        System.out.println(result);
    }


}
