package bfs.basic;

// ���� 4 : 1���������� BFS

import java.util.*;
import java.io.*;

public class Boj1697 {
	static int maxX = 200005;
	static int[] time = new int[maxX];
	static char[] board = new char[maxX];
	static int[] moveX = {-1, 1};
	static int teleport = 2;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int me = Integer.parseInt(st.nextToken());
		int you = Integer.parseInt(st.nextToken());
		
		board[me] = 'I';  board[you] = 'U';
		Arrays.fill(time, -1);
		
		Queue<Integer> q = new LinkedList();
		q.add(me);
		time[me] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(board[cur] == 'U') {
				System.out.println(time[cur]);
				return;
			}
			for(int i = 0; i < 3; i++) {
				int nx;
				// walk
				if(i < 2) { 
					nx = cur + moveX[i];
				}
				// teleport
				else { 
					nx = cur * 2;
				}
				if(nx < 0) continue;
				if(nx >= 200005 || time[nx] != -1) continue;
				
				q.add(nx);
				time[nx] = time[cur] + 1;
			}
		}
	}
}
