//회의실 배정
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1931 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Point[] jobs;
    static int n;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < jobs.length; i++) {
            choice(jobs[i]);
        }
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        jobs = new Point[n];
        for (int i = 0; i < jobs.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            jobs[i] = new Point(x, y);
        }
        Arrays.sort(jobs, (p, q) -> {
            return p.y - q.y;
        });
    }

    static void choice(Point job) {

    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
