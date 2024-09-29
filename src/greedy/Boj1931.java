//회의실 배정
package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1931 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Job[] jobs;
    static Job latestJob = new Job(0, 0);
    static int n;
    static int count;
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < jobs.length; i++) {
            choice(jobs[i]);
        }
        System.out.println(count);
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        jobs = new Job[n];
        for (int i = 0; i < jobs.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            jobs[i] = new Job(start, end);
        }
        Arrays.sort(jobs, (p, q) -> {
            if(p.end == q.end) {
                return p.start - q.start;
            } else {
                return p.end - q.end;
            }
        });
    }

    static void choice(Job job) {
        if(latestJob.end > job.start) { //최근에 추가된 작업과 겹치는 경우
            return;
        }
        latestJob = job;
        count++;
    }

    static class Job {
        public int start;
        public int end;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
