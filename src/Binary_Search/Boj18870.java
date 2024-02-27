//좌표 압축
/*dp*/
package Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18870 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Number[] numbers;

    public static void main(String[] args) throws Exception {
        init();
        dp();
        printAnswer();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        numbers = new Number[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = new Number(i, num);
        }

        Arrays.sort(numbers, (n1, n2) -> {
            return n1.num - n2.num;
        });
    }

    static void dp() {
        for (int i = 1; i < n; i++) {
            if (numbers[i].num == numbers[i - 1].num) {
                numbers[i].count = numbers[i - 1].count;
            } else {
                numbers[i].count = numbers[i - 1].count + 1;
            }
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(numbers, (n1, n2) -> {
            return n1.index - n2.index;
        });

        for (Number number : numbers) {
            sb.append(number.count).append(" ");
        }
        System.out.println(sb);
    }

    static class Number {
        int index;
        int num;
        int count;

        public Number(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
}
