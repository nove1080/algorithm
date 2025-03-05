package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea1952 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int[] calendar; //수영장에 다닐 날
    static int[] fee; //이용권 가격
    
    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            backtracking(0, 0);
            sb.append("#").append(t).append(" ").append(Math.min(answer, fee[3])).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = Integer.MAX_VALUE;

        fee = new int[4];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            fee[i] = Integer.parseInt(input[i]);
        }

        calendar = new int[12];
        input = br.readLine().split(" ");
        for (int i = 0; i < 12; i++) {
            calendar[i] = Integer.parseInt(input[i]);
        }
    }

    public static void backtracking(int nextMonth, int currentFee) {
        if (currentFee > answer || currentFee > fee[3]) return;
        if (nextMonth >= 12) {
            answer = Math.min(currentFee, answer);
            return;
        }

        backtracking(nextMonth + 1, currentFee + fee[0] * calendar[nextMonth]); //1일 이용권
        backtracking(nextMonth + 1, currentFee + fee[1]); //1달 이용권
        backtracking(nextMonth + 3, currentFee + fee[2]); //3달 이용권
    }
}
