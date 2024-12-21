package bitmasking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Boj15787 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static HashMap<String, Boolean> map = new LinkedHashMap<>();
    static Train[] trains;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solve());
    }

    public static void init() throws Exception {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        trains = new Train[n];
        for (int i = 0; i < n; i++) {
            trains[i] = new Train();
        }

        m = Integer.parseInt(input[1]);
        for (int i = 0; i < m; i++) {
            String[] command = br.readLine().split(" ");

            int trainNum = Integer.parseInt(command[1]) - 1;
            switch (command[0]) {
                case "1":
                    trains[trainNum].add(Integer.parseInt(command[2]));
                    break;
                case "2":
                    trains[trainNum].remove(Integer.parseInt(command[2]));
                    break;
                case "3":
                    trains[trainNum].moveBackward();
                    break;
                case "4":
                    trains[trainNum].moveForward();
                    break;
            }
        }
    }

    public static int solve() {
        int cnt = 0;
        for (Train train : trains) {
            String key = train.getHash();
            System.out.println("Train = " + key);
            if (!map.containsKey(key)) {
                map.put(key, true);
                System.out.println("\tPUT " + key);
                cnt++;
            }
        }
        return cnt;
    }

    static class Train {
        int head = 20;
        boolean[] arr = new boolean[60];

        public void moveForward() {
            if (arr[head]) {
                arr[head] = false;
            }
            if (head > 0) {
                head++;
            }
        }
        public void moveBackward() {
            if (head + 19 < 59) {
                if (arr[head + 19]) {
                    arr[head + 19] = false;
                }
            }

            if (head < 59) {
                head--;
            }
        }
        public void add(int pos) {
            arr[head + pos - 1] = true;
        }
        public void remove(int pos) {
            arr[head + pos - 1] = false;
        }
        public String getHash() {
            StringBuilder sb = new StringBuilder();
            //moveBackward 엄청~하면 이거 overflow 나니까 다른 방법 고안하기
            if (head + 19 < 59) {
                for (int i = 0; i < 20; i++) {
                    if (arr[head + i]) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            } else {
                int diff = 59 - head;
                for (int i = 0; i <= diff; i++) {
                    if (arr[head + i]) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }

                for (int i = 0; i < 20 - diff - 1; i++) {
                    sb.append(0);
                }
            }

            return sb.toString();
        }
    }

    public static void bitMasking() {

    }
}
