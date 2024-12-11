//게리맨더링
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj17471 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] nums;
    static int[] population;
    static ArrayList<Integer>[] adjList;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        //조합 + BFS
        for (int i = 1; i <= n / 2; i++) {
            comb(nums, new boolean[n], 0, n, i);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        population = new int[n];
        nums = new int[n];

        for (int i = 0; i < n; i++) {
            population[i] = Integer.parseInt(input[i]);
            nums[i] = i;
        }

        adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            adjList[i] = new ArrayList(Integer.parseInt(input[0]));
            for (int j = 1; j < input.length; j++) {
                adjList[i].add(Integer.parseInt(input[j]) - 1);
            }
        }
    }

    /**
     * 재귀를 이용한 조합(nCr)을 수행합니다.
     *
     * @param arr   조합에 사용될 원소
     * @param vis   선택된 원소를 표기할 boolean[]
     * @param start 고려할 원소의 시작 인덱스 (start 이상 인덱스부터 조합의 대상이 된다.)
     * @param n     nCr의 n
     * @param r     nCr의 r
     */
    public static void comb(int[] arr, boolean[] vis, int start, int n, int r) {
        if (r == 0) {
            int result = bfs(arr, vis);
            if (result >= 0) {
                answer = Math.min(result, answer);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            vis[i] = true;
            comb(arr, vis, i + 1, n, r - 1);
            vis[i] = false;
        }
    }

    public static int bfs(int[] arr, boolean[] vis) {
        Queue<Integer> trueQ = new LinkedList<>();
        boolean[] trueVis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (vis[i]) { //조합을 통해 뽑힌 구역
                trueQ.add(arr[i]);
                trueVis[i] = true;

                while (!trueQ.isEmpty()) {
                    Integer cur = trueQ.poll();
                    for (Integer next : adjList[cur]) {
                        if (trueVis[next] || !vis[next]) {
                            continue;
                        }
                        trueQ.add(next);
                        trueVis[next] = true;
                    }
                }
                break;
            }
        }

        Queue<Integer> falseQ = new LinkedList<>();
        boolean[] falseVis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                falseQ.add(arr[i]);
                falseVis[i] = true;

                while (!falseQ.isEmpty()) {
                    Integer cur = falseQ.poll();
                    for (Integer next : adjList[cur]) {
                        if (falseVis[next] || vis[next]) {
                            continue;
                        }
                        falseQ.add(next);
                        falseVis[next] = true;
                    }
                }
                break;
            }
        }

        //인구 차이 계산
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (vis[i] && trueVis[i]) {
                result += population[i];
            } else if (!vis[i] && falseVis[i]) {
                result -= population[i];
            } else {
                return -1;
            }
        }

        return Math.abs(result);
    }

}


