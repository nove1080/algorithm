package Bfs.basic;
import java.util.LinkedList;
import java.util.Queue;



// �⺻ BFS �ڵ�
public class BFS_code {
	
	static int[][] board = new int[100][100];
	static boolean[][] vis = new boolean[100][100];
	static int maxX = 7, maxY = 10;
	static int[] moveX = {1,0,-1,0};
	static int[] moveY = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		Queue<Point> q = new LinkedList();
		vis[0][0] = true;
		q.add(new Point(0,0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			System.out.printf("(%d, %d) -> ", cur.x, cur.y);
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + moveX[dir];	// nx = Next X;
				int ny = cur.y + moveY[dir];
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(vis[nx][ny] || board[nx][ny] != 1) continue;
				
				vis[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}
	}

	static class Point {
		int x, y;

		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
