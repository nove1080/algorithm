package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Swea2382 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int answer;

    static int n; //board 크기
    static int k; //미생물 군집의 수
    static int m; //격리 시간

    //상 하 좌 우
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    static Map<String, Point> map;

    public static void main(String[] args) throws Exception {

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            init();

            simulate();

            for (Point cell : map.values()) {
                answer += cell.micro;
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        answer = 0;

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            int micro = Integer.parseInt(input[2]);
            int dir = Integer.parseInt(input[3]) - 1;

            map.put(getPos(r, c), new Point(r, c, micro, dir));
        }
    }

    public static void simulate() {
        while (m-- > 0) {
            HashMap<String, List<Point>> tempMap = new HashMap<>();
            for (Point cell : map.values()) {
                int nr = cell.r + moveR[cell.dir];
                int nc = cell.c + moveC[cell.dir];

                //경계에 위치
                if(nr <= 0 || nc <= 0 || nr >= n - 1 || nc >= n - 1) {
                    //미생물수 절감
                    cell.micro /= 2;
                    //방향 반전
                    if(cell.dir == 0) cell.dir = 1;
                    else if(cell.dir == 1) cell.dir = 0;
                    else if(cell.dir == 2) cell.dir = 3;
                    else cell.dir = 2;
                }

                cell.r = nr;
                cell.c = nc;

                String key = getPos(nr, nc);
                if (!tempMap.containsKey(key)) {
                    List<Point> cells = new ArrayList<>();
                    cells.add(cell);
                    tempMap.put(key, cells);
                } else {
                    tempMap.get(key).add(cell);
                }
            }

            Map<String, Point> nextMap = new HashMap<>();
            for (String key : tempMap.keySet()) {
                Point cell = tempMap.get(key).get(0);
                if(tempMap.get(key).size() > 1) {
                    cell = assimilate(tempMap.get(key));
                }

                nextMap.put(key, cell);
            }

            map = nextMap;
        }
    }

    public static Point assimilate(List<Point> cells) {
        Point assimilated = new Point(cells.get(0));
        assimilated.r = cells.get(0).r;
        assimilated.c = cells.get(0).c;

        int maxMicro = assimilated.micro;
        for (int i = 1; i < cells.size(); i++) {
            assimilated.micro += cells.get(i).micro;

            if(maxMicro < cells.get(i).micro) {
                assimilated.dir = cells.get(i).dir;
                maxMicro = cells.get(i).micro;
            }
        }

        return assimilated;
    }

    private static String getPos(int r, int c) {
        return r + ", " + c;
    }

    static class Point {
        int r, c;
        int micro;
        int dir;

        public Point(Point point) {
            this.r = point.r;
            this.c = point.c;
            this.micro = point.micro;
            this.dir = point.dir;
        }

        public Point(int r, int c, int micro, int dir) {
            this.r = r;
            this.c = c;
            this.micro = micro;
            this.dir = dir;
        }
    }
}
