//주식
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11501 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int tc;
    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());

        while (tc-- != 0) {
            //init
            int period = Integer.parseInt(br.readLine());
            int[] prices = new int[period];
            Info[] infos = new Info[period];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < period; i++) {
                int price = Integer.parseInt(st.nextToken());
                prices[i] = price;
                infos[i] = new Info(price, i);
            }
            Arrays.sort(infos); //가장 비싼 날짜 순으로 정렬

            //solve
            int sellingDay = 0;
            long stockPrice = 0;
            solution:
            for (int day = 0; day < period; day++) {
                if(day == infos[sellingDay].day) {
                    while(infos[sellingDay].day <= day) { //sellingDay를 다음 판매날로 변경
                        sellingDay++;
                        if (sellingDay >= period) { //다음 판매할 날은 없는 경우
                            break solution;
                        }
                    }
                } else { //주식을 산다.
                    stockPrice += infos[sellingDay].price - prices[day]; //판매할 날의 가격 - 현재 가를 누적
//                    System.out.println("차익["+day+"]"+(infos[sellingDay].price - prices[day]));
                }
            }
            sb.append(stockPrice).append("\n");
        }
        System.out.println(sb);
    }

    static class Info implements Comparable<Info>{
        public int price;
        public int day; //0-indexed

        public Info(int price, int day) {
            this.price = price;
            this.day = day;
        }

        @Override
        public int compareTo(Info o) {
            return o.price - this.price;
        }
    }
}
