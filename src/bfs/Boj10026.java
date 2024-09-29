// ���ϻ��� [G5]

// sol
//  1. BFS�� ���� ����
import java.io.*;
import java.util.*;

public class Boj10026 {

	static int n;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		// init
		char[][] board = new char[n][n];
//		boolean[][] vis = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < n; j++) {
				board[i][j] = tmp[j];
//				vis[i][j] = false;
			}
		}
		
		sb.append(bfs(board, false)).append(" ");
		sb.append(bfs(board, true));
		System.out.println(sb);
	}
	
	public static int bfs(char[][] board, boolean isSick) {
		int cnt = 0;
		boolean[][] vis = new boolean[n][n];
		Queue<Point> q = new LinkedList();
		
		if(isSick) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(board[i][j] == 'R') board[i][j] = 'G';
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// ������ ã��
				if(!vis[i][j]) {
					cnt++;
					char color = board[i][j];
					q.add(new Point(i, j));
					vis[i][j] = true;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int idx = 0; idx < 4; idx++) {
							int nx = cur.x + moveX[idx];
							int ny = cur.y + moveY[idx];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							if(board[nx][ny] != color || vis[nx][ny]) continue;
							
							q.add(new Point(nx, ny));
							vis[nx][ny] = true;
 						}
					}
				} // if
			} // for2
		} // for1
		
		return cnt;
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
