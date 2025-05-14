package dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2098 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int WALL = 0;
    static final int INF = 16 * 1_000_000;

    static int n;
    static int isDone;

    static int[][] costs;
    static int[][] dp;
    static int[][] path; //최적 경로

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(tsp(0, 1));
        printPath();
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        isDone = (1 << n) - 1;
        dp = new int[n][1 << n];
        path = new int[n][1 << n];

        costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(input[j]);
            }
        }
    }

    public static int tsp(int now, int visited) {
        //모든 노드를 방문 완료
        if (visited == isDone) {
            //현재 노드에서 시작점으로 가는 경로가 있다면
            if (costs[now][0] > 0) {
                return costs[now][0]; //최소 비용 반환
            }
        }

        //메모이제이션 활용: 이미 계산된 값이 있다면 중복 계산하지 않고 이를 사용함
        if (dp[now][visited] != 0) return dp[now][visited];
            //이전에 계산된 적이 없으면 계산을 시작
        else {
            dp[now][visited] = INF;
            for (int i = 0; i < n; i++) {
                //현재 노드에서 i번 노드로 가는 경로가 없으면 continue
                if (costs[now][i] == WALL) continue;
                //이미 방문한 노드라면 continue
                if ((visited & (1 << i)) != 0) continue;

                //i번 노드 방문 처리 후 최소 비용 반환
                int temp = tsp(i, visited | 1 << i) + costs[now][i];
                if (dp[now][visited] > temp) {
                    dp[now][visited] = temp;
                    path[now][visited] = i; //경로 저장
                }
            }
        }
        return dp[now][visited];
    }

    public static void printPath() {
        int now = 0;
        int visited = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(now).append(" "); // 시작 도시 출력

        while (visited != isDone) {
            int next = path[now][visited];
            sb.append(next).append(" ");
            visited |= (1 << next);
            now = next;
        }

        sb.append(0); // 다시 시작점으로 돌아오는 경로
        System.out.println("경로: " + sb);
    }


}
