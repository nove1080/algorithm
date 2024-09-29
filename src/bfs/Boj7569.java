// �丶�� [G5]

// sol
//  1. 3���� BFS
import java.io.*;
import java.util.*;
public class Boj7569 {
	static int[][][] board;
	static int[][][] vis;
	static int maxX, maxY, maxZ;
	static int[] moveX = {0,1,0,-1,0,0};
	static int[] moveY = {1,0,-1,0,0,0};
	static int[] moveZ = {0,0,0,0,1,-1};
	static Queue<Point3D> q = new LinkedList();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		maxY = Integer.parseInt(st.nextToken());
		maxX = Integer.parseInt(st.nextToken());
		maxZ = Integer.parseInt(st.nextToken());
		
		board = new int[maxZ][maxX][maxY];
		vis = new int[maxZ][maxX][maxY];
		
		// init
		for(int h = 0; h < maxZ; h++) {
			for(int i = 0; i < maxX; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < maxY; j++) {
					board[h][i][j] = Integer.parseInt(st.nextToken());
					vis[h][i][j] = -1;
					if(board[h][i][j] == 1) {
						q.add(new Point3D(h,i,j));
						vis[h][i][j] = 0;
					}
				}
			}
		}
		
		System.out.println(bfs());
	
	}
	
	static int bfs() {
		int day = 0;
		
		while(!q.isEmpty()) {
			Point3D cur = q.poll();
			
			for(int i = 0; i < 6; i++) {
				int nz = cur.z + moveZ[i];
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if (nx < 0 || nx >= maxX || ny < 0 || ny >= maxY 
						|| nz < 0 || nz >= maxZ)
					continue;
				if(board[nz][nx][ny] != 0 || vis[nz][nx][ny] != -1) continue;
				q.add(new Point3D(nz, nx, ny));
				day = vis[nz][nx][ny] = vis[cur.z][cur.x][cur.y] + 1; 
			}
		} // while
		
		if(!isClear()) return -1;
		return day;
	}
	
	static boolean isClear() {
		for(int h = 0; h < maxZ; h++) {
			for(int i = 0; i < maxX; i++) {
				for(int j = 0; j < maxY; j++) {
					if(board[h][i][j] == 0 && vis[h][i][j] == -1) return false;
				}
			}
		}
		return true;
	}

	static class Point3D {
		int x, y, z;
		Point3D(int z, int x, int y){
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
}