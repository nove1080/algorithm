package greedy;

import java.io.*;
import java.util.*;

public class Boj11000 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static PriorityQueue<Lecture> lectures = new PriorityQueue<>((l1, l2) -> l1.start - l2.start);
    static PriorityQueue<Lecture> rooms = new PriorityQueue<>((l1, l2) -> l1.end - l2.end);

    public static void main(String[] args) throws Exception {
        init();

        Lecture firstLecture = lectures.poll();
        rooms.add(firstLecture);

        while (!lectures.isEmpty()) {
            Lecture lecture = lectures.poll();
            if (rooms.peek().end > lecture.start) {
                rooms.add(lecture);
            } else {
                Lecture prev = rooms.poll();
                rooms.add(new Lecture(prev.start, lecture.end));
            }
        }

        System.out.println(rooms.size());
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int st = Integer.parseInt(input[0]);
            int en = Integer.parseInt(input[1]);
            lectures.add(new Lecture(st, en));
        }
    }

    static class Lecture {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

