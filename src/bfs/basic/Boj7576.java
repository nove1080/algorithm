package bfs.basic;
// ���� 2 : �������� ���� ���� ��

import java.io.*;
import java.util.*;

public class Boj7576 {
	
	static int[][] board;
	static int[][] day;
	static int maxX, maxY;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		maxY = Integer.parseInt(st.nextToken());
		maxX = Integer.parseInt(st.nextToken());
		
		board = new int[maxX][maxY];
		day = new int[maxX][maxY];
		
		
		Queue<Point> q = new LinkedList();
		// search : starting point
		for(int i = 0; i < maxX; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < maxY; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					q.add(new Point(i, j));
					ans = day[i][j] = 0;
				} else { 
					day[i][j] = -1; 
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int dis = 0; dis < 4; dis++) {
				int nx = cur.x + moveX[dis];
				int ny = cur.y + moveY[dis];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] != 0 || day[nx][ny] != -1) continue;
				
				q.add(new Point(nx, ny));
				day[nx][ny] = day[cur.x][cur.y] + 1;
				
				if(ans < day[nx][ny]) ans = day[nx][ny];
			}
		}
		
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				if(day[i][j] == -1 && board[i][j] == 0) {
					System.out.println("-1\n");
					return;
				}
				if(ans < day[i][j]) ans = day[i][j];
			}
		}
		
		System.out.println(ans);
	}
	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
