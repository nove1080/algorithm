//공주님의 정원
/**
 * [Greedy]
 * - 꽃: 오름차순 정렬 후 삽입
 * - 가장 짧은 기간의 꽃부터 제거가 가능한지 탐색 -> 시간 초과?
 * - 남은 것이 정답
 * [참고]
 * - https://lovelyunsh.tistory.com/82
 * - 현재 날짜보다 이전에 피기 시작하는 꽃들 중 가장 오래 피는 꽃들을 골라나간다.
 */
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2457 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static List<Flower> flowers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int count = 0;
        int today = 301;
        int maxEnd = 0;

        for (Flower flower : flowers) {
            if (today >= flower.start) {
                maxEnd = Math.max(maxEnd, flower.end);
            } else {
                if(maxEnd > today) {
                    today = maxEnd;
                    System.out.println(maxEnd);
                    count++; //꽃을 선택
                    if (today >= flower.start) {
                        maxEnd = Math.max(maxEnd, flower.end);
                    }
                }
            }
            if (today > 1130) {
                break;
            }
        }

        if(today > 1130) {
            return count;
        } else {
            return 0;
        }
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        while (n-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String start = st.nextToken();
            String startD = st.nextToken();
            if (startD.length() == 1) {
                startD = "0" + startD;
            }
            start += startD;

            String end = st.nextToken();
            String endD = st.nextToken();
            if (endD.length() == 1) {
                endD = "0" + endD;
            }
            end += endD;

            flowers.add(new Flower(start, end));
        }
        Collections.sort(flowers);
        flowers.add(new Flower("9999", "9999"));
    }

    static class Flower implements Comparable<Flower> {
        public int start;
        public int end;

        public Flower(String start, String end) {
            this.start = Integer.parseInt(start);
            this.end = Integer.parseInt(end);
        }

        @Override
        public int compareTo(Flower o) {
            return start - o.start;
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
