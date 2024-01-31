package Linked_List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj5397 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc;
    public static void main(String[] args) throws Exception {
        init();
        while (tc-- != 0) {
            //입력 -> List로 변환
            char[] input = br.readLine().toCharArray();
            List<Character> inputList = new ArrayList<>();
            for (char c : input) {
                inputList.add(c);
            }

            //비밀번호 분석
            for (int i = 0; i < input.length; i++) {
                switch (input[i]) {
                    case '<':
                    case '>':
                    case '-':
                    default:
                }
            }
        }
    }

    static void init() throws Exception {
        tc = Integer.parseInt(br.readLine());
    }

}
