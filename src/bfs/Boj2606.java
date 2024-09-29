package bfs;// ���̷��� [S3]
import java.io.*;
import java.util.*;
public class Boj2606 {
	static int[][] board;
	static int[] vis;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		board = new int[total][total];
		vis = new int[total];
		Arrays.fill(vis, 0);
		
		Queue<Integer> q = new LinkedList();
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;			
			board[a][b] = 1;			
			board[b][a] = 1;
		}
		
		q.add(0);  vis[0] = 1;
        int ans = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();  
			
			for(int i = 1; i < total; i++) {
				if(board[cur][i] == 1 && vis[i] != 1) {
					q.add(i);  vis[i] = 1;  ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
