package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1068 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int root;
    static int answer;
    static int removeNode;
    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        init();
        dfs(root);
        System.out.println(answer);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        String[] input = br.readLine().split(" ");
        for (int node = 0; node < n; node++) {
            int parent = Integer.parseInt(input[node]);
            if (parent == -1) {
                root = node;
                continue;
            }

            tree[parent].add(node);
        }

        removeNode = Integer.parseInt(br.readLine());
        tree[removeNode] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree[i].remove(Integer.valueOf(removeNode));
        }
    }

    public static void dfs(int cur) {
        if (removeNode == cur) return;
        if (tree[cur].isEmpty()) {
            answer++;
            return;
        }

        for (int child : tree[cur]) {
            dfs(child);
        }
    }
}

