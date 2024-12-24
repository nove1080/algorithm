package bitmasking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Boj15787 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static HashSet<Integer> set = new LinkedHashSet<>();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(bitMasking());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

    }



    public static int bitMasking() throws Exception{
        int[] train = new int[n];

        for (int i = 0; i < m; i++) {
            String[] command = br.readLine().split(" ");

            int trainNum = Integer.parseInt(command[1]) - 1;
            switch (command[0]) {
                case "1":
                    train[trainNum] |= (1 << (Integer.parseInt(command[2]) - 1));
                    break;
                case "2":
                    train[trainNum] &= ~(1 << (Integer.parseInt(command[2]) - 1));
                    break;
                case "3":
                    train[trainNum] <<= 1;
                    train[trainNum] &= ~1;
                    break;
                case "4":
                    train[trainNum] &= ~(1 << 20);
                    train[trainNum] >>= 1;
                    break;
            }
        }

        int answer = 0;
        for (int t : train) {
            t &= (int) ((Math.pow(2, 20)) - 1);
            System.out.println("TRAIN : " + Integer.toBinaryString(t));
            if (!set.contains(t)) {
                System.out.println("\t add!");
                set.add(t);
                answer++;
            }
        }

        return answer;
    }
}
