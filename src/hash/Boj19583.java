//싸이버개강총회
package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Boj19583 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<String, Integer> hashMap = new HashMap<>(); //참석자 명단
    static int start, end, streamingEnd;
    static int answer;
    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        start = getTime(input[0]);
        end = getTime(input[1]);
        streamingEnd = getTime(input[2]);

        //입력을 계속 받는다.
        //start 보다 이전 시간 사람은 hashMap에 저장
        //end~streamingEnd 사이 사람은 hashMap에서 조회 후 존재하면 answer++
        //answer 출력한다.

        String temp;
        while((temp = br.readLine()) != null) {
            String[] input2 = temp.split(" ");
            int log = getTime(input2[0]);

            if (log <= start) {
                hashMap.put(input2[1], 0);
            }

            if (end <= log && log <= streamingEnd) {
                if (hashMap.containsKey(input2[1]) && hashMap.get(input2[1]) == 0) {
                    answer++;
                    hashMap.put(input2[1], hashMap.get(input2[1]) + 1);
                }
            }
        }

        System.out.println(answer);
    }

    private static Integer getTime(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0))
            .append(input.charAt(1))
            .append(input.charAt(3))
            .append(input.charAt(4));

        return Integer.parseInt(sb.toString());
    }

}
