//가장 큰 정사각형
/**
 * bfs
 */
package Dynamic_Programming.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1915 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int maxR, maxC;
    static int[][] table;
    static int[] moveX = {1,0,-1,0};
    static int[] moveY = {0,1,0,-1};

    public static void main(String[] args) throws Exception {
        init();
        printTable();
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxR = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());

        table = new int[maxR][maxC];
        for (int i = 0; i < maxR; i++) {
            char[] dots = br.readLine().toCharArray();
            for (int j = 0; j < maxC; j++) {
                table[i][j] = dots[j] - '0';
            }
        }
    }

    static void bfs() {

    }

    static void printTable() {
        System.out.println("=================");
        for (int[] ints : table) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
