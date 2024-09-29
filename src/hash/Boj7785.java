package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Boj7785 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static HashMap<String, String> employees = new HashMap<>();

    public static void main(String[] args) throws Exception {
        solve();
        printAnswer();
    }

    static void solve() throws Exception {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();
            employees.put(name, status);
        }
    }
    
    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        List<String> names = new ArrayList<>();
        for(String name : employees.keySet()) {
            if(employees.get(name).equals("enter")) {
                names.add(name);
            }
        }

        names.sort(Collections.reverseOrder());

        for(String name : names) {
            sb.append(name)
              .append("\n");
        }

        System.out.println(sb);
    }
}
