package twopointer;//12891: DNA 비밀번호
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Boj12891 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int p;
    static char[] dnaCarr;

    static int[] alphabetCondition;
    static Map<Character, Integer> alphaToIndexMapper = new HashMap<>(); //key: 알파벳, value: key에 해당하는 인덱스

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(twoPointer());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        p = Integer.parseInt(input[1]);

        dnaCarr = br.readLine().toCharArray();

        input = br.readLine().split(" ");
        alphaToIndexMapper.put('A', 0);
        alphaToIndexMapper.put('C', 1);
        alphaToIndexMapper.put('G', 2);
        alphaToIndexMapper.put('T', 3);

        alphabetCondition = new int[4];
        alphabetCondition[0] = Integer.parseInt(input[0]);
        alphabetCondition[1] = Integer.parseInt(input[1]);
        alphabetCondition[2] = Integer.parseInt(input[2]);
        alphabetCondition[3] = Integer.parseInt(input[3]);
    }

    public static int twoPointer() {
        int answer = 0;
        //초기 부분 문자열 생성
        int[] alphabet = new int[4];
        int end;
        for (end = 0; end < p; end++) {
            alphabet[alphaToIndexMapper.get(dnaCarr[end])]++;
        }

        end--;
        for (int st = 0; end < n; st++, end++) {
            if(st > 0) {
                alphabet[getIndexBasedDnaCArr(st - 1)]--;
                alphabet[getIndexBasedDnaCArr(end)]++;
            }

            //가능한 부분 문자열인지 확인
            for (int i = 0; i < 4; i++) {
                if(alphabetCondition[i] > alphabet[i]) break;
                if(i == 3) answer++;
            }
        }

        return answer;
    }

    /**
     * 제공된 DNA 문자열에 해당하는 인덱스를 반환
     * @param index 제공된 DNA 배열 상 인덱스
     * @return 인덱스
     */
    private static Integer getIndexBasedDnaCArr(int index) {
        return alphaToIndexMapper.get(dnaCarr[index]);
    }
}
