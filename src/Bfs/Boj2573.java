

// sol
//  1. bfs�� �� ���ϸ� ��ŭ �������� ���ÿ� ī��Ʈ�Ѵ�.
import java.io.*;
import java.util.*;

public class Boj2573 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] board;
	static int[][] meltCnt;
	static boolean[][] vis;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	static int maxX, maxY;
	static int ans;
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		
		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		
		board = new int[maxX][maxY];
		meltCnt = new int[maxX][maxY];
		vis = new boolean[maxX][maxY];
		
		initBoard();
		
		while(true) {
			int cnt = 0;
			initMeltNVis();
			
			for(int i = 0; i < maxX; i++) {
				for(int j = 0; j < maxY; j++) {
					if(board[i][j] > 0 && !vis[i][j]) {
//						System.out.println(board[i][j] + "hi");
						cnt++;
						bfs(new Point(i, j));
					}
				} // for2
			} // for1
			
			melting();
			
//			printB();
			if(cnt >= 2) break;
			if(cnt == 0) {
				System.out.println(0);
				return;
			}
			ans++;
			
		} // while
		System.out.println(ans);
	}

	static void initBoard() throws Exception{
		for(int i = 0; i < maxX; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < maxY; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void initMeltNVis() {
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				meltCnt[i][j] = 0;
				vis[i][j] = false;
			}
		}
	}
	
	// meltCnt �ʱ�ȭ + ��������
	static void bfs(Point p) {
		Queue<Point> q = new LinkedList();
		q.add(p);
		vis[p.x][p.y] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] == 0) {
					meltCnt[cur.x][cur.y]--; 
					continue;
				}
				if(vis[nx][ny]) continue;
				
				q.add(new Point(nx, ny));
				vis[nx][ny] = true;
			}
		}
	}
	
	static void melting() {
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				board[i][j] += meltCnt[i][j];
				if(board[i][j] < 0) board[i][j] = 0;
			}
		}
	}
	
}

//class Point {
//	int x, y;
//	Point(int x, int y){
//		this.x = x;
//		this.y = y;
//	}
//}