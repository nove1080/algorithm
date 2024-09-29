package bfs.basic;
// �׸� [S1]

// sol
//  1. �⺻ BFS
import java.io.*;
import java.util.*;


public class Boj1926 {
	static int[][] board;
	static boolean[][] vis;
	static int[] moveX = {0,1,0,1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// init : basic bfs variable
		board = new int[n][m];
		vis = new boolean[n][m];
		
		// declare2 : additional variable
		int maxSize = 0, numOfPic = 0;
		
		// init : board and vis
		for(int i = 0; i < n; i++) {
			StringTokenizer boardVal = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(boardVal.nextToken());
				vis[i][j] = false;
			}
		}
		
		Queue<Point> q = new LinkedList();
		
		// bfs
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				// search : starting point
				if(board[i][j] == 1 && !vis[i][j]) {
					q.add(new Point(i, j)); numOfPic++;
					vis[i][j] = true;
					
					int picSize = 0;
					while(!q.isEmpty()) {
						Point cur = q.poll(); picSize++; 
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = cur.x + moveX[dir];
							int ny = cur.y + moveY[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
							if(board[nx][ny] != 1 || vis[nx][ny]) continue;
							
							q.add(new Point(nx, ny));
							vis[nx][ny] = true;
						} // for3
					} // while
					
					maxSize = Math.max(maxSize, picSize);
				} 
			} // for2
		} // for1
		
		System.out.println(numOfPic + "\n" + maxSize);
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
