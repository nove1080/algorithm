//SWEA 2948
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        sc.nextLine();

        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] inputs = sc.nextLine().split(" ");
            int strASize = Integer.parseInt(inputs[0]);
            int strBSize = Integer.parseInt(inputs[1]);

            String[] strA = sc.nextLine().split(" ");
            String[] strB = sc.nextLine().split(" ");

            int answer = 0;
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (String s : strA) {
                hashMap.put(s, 0);
            }

            for(String s : strB) {
                if (hashMap.containsKey(s)) {
                    answer++;
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
