import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5525 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int s, n;
    static char[] cArr;
    static int answer;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(twoPointer());
    }

    public static void init() throws Exception {
        s = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        cArr = br.readLine().toCharArray();
    }

    public static int twoPointer() {
        int result = 0;

        int st = 0;
        while(st < n - 1 && cArr[st] == 'O') st++;

        int count = 1;

        char prev = cArr[st]; //'I'
        for (int en = st + 1; en < n; en++) {
            if (cArr[en] != prev) { //S에 Pn이 포함되고? 있는 경우
                count++;
                prev = cArr[en];
                if (count == 2 * s + 1) { //S에 Pn이 포함된 경우
//                    System.out.println("st = " + st + " same");
                    st += 2;
                    count -= 2;
                    result++;
                }
                continue;
            }

            if (cArr[en] == prev) { //가리키고 있는 대상이 Pn이 아닌 경우 ex) II 혹은 OO와 같은 패턴 발견
                st = en;
                while(st < n - 1 && cArr[st] == 'O') {
                    st++;
                }
                en = st;
                prev = cArr[en];
                count = 1;
            }
        }

        return result;
    }

}
