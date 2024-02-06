//잃어버린 괄호
/**
 * 55 - (50 + 40) = -35
 * [풀이]
 * - 가 등장하면 괄호를 사용하여 가장 크게 만들어준다
 *
 * + -> 앞 뒤의 숫자를 더해준다
 * - -> 이제부터 minus 영역에서 연산할 것을 표시해둔다.
 */
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1541 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        solve();
    }

    static void solve() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int sum = 0;
        int buffer = 0;
        char[] positive = st.nextToken().toCharArray();
        for (int i = 0; i < positive.length; i++) {
            if(positive[i] == '+') {
                sum += buffer;
                buffer = 0;
                continue;
            }
            buffer *= 10;
            buffer += positive[i] - '0';
        }
        sum += buffer;
        buffer = 0;

        while(st.hasMoreTokens()){
            char[] negative = st.nextToken().toCharArray();
            for (int i = 0; i < negative.length; i++) {
                if(negative[i] == '+') {
                    sum -= buffer;
                    buffer = 0;
                    continue;
                }
                buffer *= 10;
                buffer += negative[i] - '0';
            }
            sum -= buffer;
            buffer = 0;
        }
        System.out.println(sum);
    }

}
