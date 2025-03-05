package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj17281 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxInning;
    final static int MAX_PLAYER = 9;

    static int[] squad;
    static int[][] scores;

    static boolean[] vis;

    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        init();
        simulation(0);
        System.out.println(maxScore);
    }

    public static void init() throws Exception {
        maxInning = Integer.parseInt(br.readLine());

        squad = new int[MAX_PLAYER];
        vis = new boolean[MAX_PLAYER];
        scores = new int[maxInning][MAX_PLAYER];
        for (int inning = 0; inning < maxInning; inning++) {
            String[] input = br.readLine().split(" ");
            for (int player = 0; player < MAX_PLAYER; player++) {
                scores[inning][player] = Integer.parseInt(input[player]);
            }
        }

        //4번 타자는 1번 선수
        vis[0] = true;
        squad[3] = 0;
    }

    public static void simulation(int depth) {
        if(depth == 3) {
            squad[3] = 0;
            simulation(depth + 1);
            return;
        }

        if(depth == 9) { //선수 선발이 끝남
            maxScore = Math.max(maxScore, play());
            return;
        }

        for (int i = 1; i < MAX_PLAYER; i++) {
            if (vis[i]) continue;

            vis[i] = true;
            squad[depth] = i;
            simulation(depth + 1);
            vis[i] = false;
        }
    }

    public static int play() {
        DashBoard dashBoard = new DashBoard();

        int player = 0;
        while (!dashBoard.gameOver()) {
            dashBoard.hit(scores[dashBoard.inning][squad[player]]);
            player = (player + 1) % 9;
        }

        return dashBoard.score;
    }

    static class DashBoard {
        final static int MAX_PLAYER = 3;
        int[] players;
        int score;
        int inning;
        int outCount;

        public DashBoard() {
            players = new int[MAX_PLAYER];
        }

        public void hit(int hitType) {
            //아웃
            if (hitType == 0) {
                outCount++;
                if (outCount == 3) {
                    inning++;
                    outCount = 0;
                    players = new int[MAX_PLAYER];
                }
            }
            
            //주자 베이스 옮기기
            for (int i = 0; i < MAX_PLAYER; i++) {
                if (players[i] != 0) {
                    players[i] += hitType;

                    if (players[i] >= 4) {
                        score++;
                        players[i] = 0;
                    }
                }
            }

            //현재 타자 빈 베이스에 등록시키기
            if (hitType != 4) {
                for (int i = 0; i < MAX_PLAYER; i++) {
                    if (players[i] == 0) {
                        players[i] = hitType;
                        break;
                    }
                }
            } else { //홈런
                score++;
            }
        }

        public boolean gameOver() {
            return inning >= maxInning;
        }
    }
}
