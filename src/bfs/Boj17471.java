//게리맨더링
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj17471 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] populations;
    static List<Integer>[] adjList;
    static int minimumDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        int tryCount = n / 2;
        for (int i = 1; i <= tryCount; i++) {
            gerrymandering(i, new ArrayList<>());
        }

        if (minimumDifference == Integer.MAX_VALUE) {
            minimumDifference = -1;
        }
        System.out.println(minimumDifference);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        populations = new int[n];
        adjList = new ArrayList[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            populations[i] = Integer.parseInt(input[i]);
            adjList[i] = new ArrayList<>();
        }

        //그래프 정보 초기화
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            int connectedCount = Integer.parseInt(input[0]);

            for (int j = 1; j <= connectedCount; j++) {
                int connectedNum = Integer.parseInt(input[j]) - 1;
                adjList[i].add(connectedNum);
                adjList[connectedNum].add(i);
            }
        }
    }

    public static void gerrymandering(int chooseCount, List<Integer> area) {
        if (chooseCount == 0) {
            List<Integer> others = makeOthers(area);
            if (isConnected(area) && isConnected(others)) {
                minimumDifference = Math.min(minimumDifference, getDifference(area, others));
            }
        }

        for (int i = 0; i < n; i++) {
            if(!area.contains(i)) {
                area.add(i);
                gerrymandering(chooseCount - 1, area);
                area.remove(area.size() - 1);
            }
        }
    }

    //파라미터로 전달받은 선거구에 속하지 않은 다른 지역들을 선거구로 만든다.
    private static List<Integer> makeOthers(List<Integer> area) {
        List<Integer> others = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(!area.contains(i)) {
                others.add(i);
            }
        }
        return others;
    }

    //bfs로 각 구역이 연결되어 있는지 확인
    public static boolean isConnected(List<Integer> area) {
        List<Integer> vis = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(area.get(0));
        vis.add(area.get(0));

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (Integer next : adjList[cur]) {
                if(!area.contains(next) || vis.contains(next)) continue;
                q.add(next);
                vis.add(next);
            }
        }

        return isAllConnected(area, vis);
    }

    private static boolean isAllConnected(List<Integer> area, List<Integer> vis) {
        return area.size() == vis.size();
    }

    //두 선거구의 인구 차를 반환
    public static int getDifference(List<Integer> areaA, List<Integer> areaB) {
        int populationA = 0;
        for (Integer a : areaA) {
            populationA += populations[a];
        }

        int populationB = 0;
        for (Integer b : areaB) {
            populationB += populations[b];
        }
        return Math.abs(populationA - populationB);
    }

    private static void debugging(List<Integer> area, List<Integer> others) {
        System.out.println("====== 게리멘더링 결과 ======");
        System.out.print("[area] : ");
        for (Integer i : area) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.print("[others] : ");
        for (Integer i : others) {
            System.out.print(i + ", ");
        }
        System.out.println();
        minimumDifference = Math.min(minimumDifference, getDifference(area, others));
        System.out.println("인구 차이 : " + minimumDifference);
    }
}
