package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17136 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n = 10;

    static int[][] paper;
    static int[][] vis; //색종이로 덮혔는지 유무
    static int[] stock = {5, 5, 5, 5, 5}; //색종이의 재고

    static int totalOnes; //1의 총 개수
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        
        //가장 최초로 1인 지점을 만나면 해당 지점부터 backtracking 을 시작하면 틀린이유가 뭘까요? ㅜㅜ
        OUTER:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(paper[i][j] != 0) {
                    backtracking(i, j, 0, 0);
                    break OUTER;
                }
            }
        }
//        backtracking(0, 0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void init() throws Exception {
        vis = new int[n][n];
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(input[j]);

                if (paper[i][j] == 1) totalOnes++;
            }
        }
    }

    /**
     *
     * @param r 탐색 대상이 되는 행
     * @param c 탐색 대상이 되는 열
     * @param depth 사용한 색종이의 수
     * @param area 붙인 색종이의 총 면적
     */
    public static void backtracking(int r, int c, int depth, int area) {
        if (answer <= depth) { //더 많거나 같은 수의 색종이를 쓴 경우
            return;
        }
        if (area == totalOnes) { //종이를 다 덮은 경우
            answer = Math.min(answer, depth);
            return;
        }
        if (c >= n) {
            backtracking(r + 1, 0, depth, area);
            return;
        }
        if (r >= n) return;
        if (paper[r][c] == 0 || vis[r][c] != 0) { //이미 색종이가 붙었거나, 붙이면 안되는 경우
            backtracking(r, c + 1, depth, area); //다음 좌표로 이동
            return;
        }

        for (int type = 4; type >= 0; type--) { //큰 색종이부터 시도
            if (stock[type] <= 0) continue;

            int paperHeight = type + 1;
            boolean isPossible = true;
            SIZE_CHECK:
            for (int i = 0; i < paperHeight; i++) {
                for (int j = 0; j < paperHeight; j++) {
                    if (r + i >= n || c + j >= n) { //색종이를 붙일 공간에 대한 범위 체크
                        isPossible = false;
                        break SIZE_CHECK;
                    }
                    if (vis[r + i][c + j] != 0 || paper[r + i][c + j] == 0) { //색종이를 붙인 적이 있거나 붙이면 안되는 공간
                        isPossible = false;
                        break SIZE_CHECK;
                    }
                }
            }

            if (isPossible) { //색종이 붙이기
                attach(r, c, paperHeight, paperHeight); stock[type]--;
                backtracking(r, c + 1, depth + 1, area + paperHeight * paperHeight);
                attach(r, c, paperHeight, 0); stock[type]++;
            }
        }
    }

    private static void attach(int r, int c, int paperHeight, int mark) {
        for (int i = 0; i < paperHeight; i++) {
            for (int j = 0; j < paperHeight; j++) {
                vis[r + i][c + j] = mark;
            }
        }
    }
}
