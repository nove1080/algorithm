package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14891_bitmasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int k;
    static int[] gears;
    static int[][] commands;
    static int[] dirs;

    public static void main(String[] args) throws Exception {
        init();
        simulate();
        System.out.println(getScore());
    }

    public static void init() throws Exception {
        dirs = new int[4];

        //init gears
        gears = new int[4];
        for (int i = 0; i < 4; i++) {
            String binaryString = br.readLine().replaceAll(" ", "");
            gears[i] = Integer.parseInt(binaryString, 2);
        }

        k = Integer.parseInt(br.readLine());

        //init command
        commands = new int[k][2];
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            commands[i][0] = Integer.parseInt(input[0]) - 1; //회전할 기어
            commands[i][1] = Integer.parseInt(input[1]); //회전 방향
        }
    }

    public static void simulate() {
        for (int[] command : commands) {
            Arrays.fill(dirs, 0);

            dirs[command[0]] = command[1];
            propagate(command[0] + 1, command[1] * -1, 1);
            propagate(command[0] - 1, command[1] * -1, -1);

            for (int i = 0; i < 4; i++) {
                gears[i] = rotateGear(gears[i], dirs[i]);
            }
        }
    }

    private static void propagate(int gear, int rotateDir, int propagateDir) {
        if(gear < 0 || gear > 3) {
            return;
        }

        // 회전 가능한지 판단
        int topTo   = 0;
        int topFrom = 0;

        if(propagateDir > 0) { //우측으로 전파되는 경우
            topTo   = (gears[gear] & (1 << 1)) >> 1;
            topFrom = (gears[gear - propagateDir] & (1 << 5)) >> 5;
        } else if(propagateDir < 0) { //좌측으로 전파되는 경우
            topTo   = (gears[gear] & (1 << 5)) >> 5;
            topFrom = (gears[gear - propagateDir] & (1 << 1)) >> 1;
        }

        if(topTo == topFrom) return;
        // 기어 회전 방향 저장
        dirs[gear] = rotateDir;

        // 기어 회전 전파
        propagate(gear + propagateDir, rotateDir * -1, propagateDir);
    }

    private static int rotateGear(int gear, int dir) {
        if(dir == 1) { //반시계
            int bottom = (gear & 1) != 0 ? 1 : 0 ;
            gear = (gear >> 1) | (bottom << 7);
        } else if(dir == -1) { //시계 방향
            int top = (gear & (1 << 7)) != 0 ? 1 : 0;
            gear = (gear << 1) | top;
        }

        int size = Integer.parseInt("11111111", 2);
        return gear & size;
    }

    private static int getScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            int top = (gears[i] & (1 << 7)) >> 7;
            score += (top * Math.pow(2, i));
        }

        return score;
    }
}

