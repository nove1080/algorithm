package Bfs;
// ��� ���� [G5]

// sol
//  1. 3���� BFS
import java.util.*;
import java.io.*;
public class Boj6593 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][][] board;
	static int[][][] time;
	static int maxZ, maxX, maxY;
	static int[] moveX = {0,1,0,-1,0,0};
	static int[] moveY = {1,0,-1,0,0,0};
	static int[] moveZ = {0,0,0,0,1,-1};
	
	static Queue<Point3D> q;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			maxZ = Integer.parseInt(st.nextToken());
			maxX = Integer.parseInt(st.nextToken());
			maxY = Integer.parseInt(st.nextToken());

			if(maxZ == 0 && maxX == 0 && maxY == 0) break;
			
			board = new char[maxZ][maxX][maxY];
			time = new int[maxZ][maxX][maxY];
			q = new LinkedList();
			
			initBoard();
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb);
	}
	static void initBoard() throws Exception{
		for(int k = 0; k < maxZ; k++) {
			for(int i = 0; i < maxX; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j = 0; j < maxY; j++) {
					board[k][i][j] = tmp[j];
					time[k][i][j] = Integer.MAX_VALUE;
					
					if(tmp[j] == 'S') {
						q.add(new Point3D(k, i, j));
						time[k][i][j] = 0;
					}
				}
			}
			br.readLine();
		}
	}
	
	
	static String bfs() {
		
		while(!q.isEmpty()) {
			Point3D cur = q.poll();
			if(board[cur.z][cur.x][cur.y] == 'E') return "Escaped in "+ time[cur.z][cur.x][cur.y] +" minute(s).";
			for(int i = 0; i < 6; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				int nz = cur.z + moveZ[i];
				
				if(nx < 0 || ny < 0 || nz < 0) continue;
				if(nx >= maxX || ny >= maxY || nz >= maxZ) continue;
				if(board[nz][nx][ny] == '#' || time[nz][nx][ny] <= time[cur.z][cur.x][cur.y] + 1) continue;
				
				q.add(new Point3D(nz, nx, ny));
				time[nz][nx][ny] = time[cur.z][cur.x][cur.y] + 1; 
			}
		}
		return "Trapped!";
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
