package Bfs.basic;
// ���� 1 : �Ÿ� ����

import java.io.*;
import java.util.*;


public class Boj2178 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int maxX, maxY;
	static int[][] board;
	static int[][] dist;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		board = new int[maxX][maxY];
		dist = new int[maxX][maxY];
		
		// init : board, dist
		for(int i = 0; i < maxX; i++) {
			char[] boardVal = br.readLine().toCharArray();
			for(int j = 0; j < maxY; j++) {
				board[i][j] = boardVal[j] - '0';
				dist[i][j] = -1;
			}
		}
		
		Queue<Point> q = new LinkedList();
		q.add(new Point(0,0));
		dist[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();   
			
			for(int dis = 0; dis < 4; dis++) {
				int nx = cur.x + moveX[dis];
				int ny = cur.y + moveY[dis];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] != 1 || dist[nx][ny] != -1) continue;
				
				q.add(new Point(nx, ny));
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
			}
		}
		System.out.println(dist[maxX-1][maxY-1]);
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
