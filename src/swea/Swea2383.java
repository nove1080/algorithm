package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Swea2383 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n;

    static List<Point> people;
    static int[][] visited;
    static Stair[] stairs;

    static int[][] deptTimes; //1번 계단으로 도착하는 시간

    static boolean[] check; // true -> idx 사람은 1번 계단

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();
            for (int i = 0; i <= people.size() / 2; i++) {
                comb(-1, 0);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        n = Integer.parseInt(br.readLine());

        int stairIdx = 0;
        stairs = new Stair[2];
        people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(input[j]);
                if(value == 0) continue;
                if(value == 1) people.add(new Point(i, j));
                else stairs[stairIdx] = new Stair(stairIdx++, new Point(i, j), value, 0, new int[3]);
            }
        }
        initDeptTimes();

        check = new boolean[people.size()];
    }

    public static void initDeptTimes() {
        deptTimes = new int[2][people.size()];
        for (int i = 0; i < people.size(); i++) {
            deptTimes[0][i] = getDeptTime(people.get(i), stairs[0].pos);
            deptTimes[1][i] = getDeptTime(people.get(i), stairs[1].pos);
        }

        System.out.println("INIT DEPT TIMES...");
        for (int i = 0; i < people.size(); i++) {
            System.out.print("people " + i + " = " + people.get(i) + " / deptTimes = "+ deptTimes[0][i] + " " + deptTimes[1][i]);
            System.out.println();
        }
    }

    private static int getDeptTime(Point p1, Point p2) {
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    public static void simulate() {
        
    }

    public static void comb(int start, int r) {
        if(r == 0) {
            simulate();
        }

        for (int next = start + 1; next < people.size(); next++) {
            check[next] = true;
            comb(next, r - 1);
            check[next] = false;
        }
    }

    static class Stair {
        int num; //계단 번호
        Point pos;
        int length; //계단 길이
        int people; //내려가고 있는 사람 수
        int[] time; //경과 시간

        public Stair(int num, Point pos, int length, int people, int[] time) {
            this.num = num;
            this.pos = pos;
            this.length = length;
            this.people = people;
            this.time = time;
        }

        public void down() {
            for (int i = 0; i < 3; i++) {
                if (++time[i] >= length) people--;
            }
        }

        public boolean in() {
            for (int i = 0; i < 3; i++) { //빈 공간에 사람을 넣음
                if (time[i] >= length) { //빈 공간 발견
                    time[i] = 0;
                    people++;
                    return true;
                }
            }

            return false;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
