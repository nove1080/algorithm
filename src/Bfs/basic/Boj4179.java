package Bfs.basic;
// ���� 3 : �������� �� ������ ��

import java.io.*;
import java.util.*;

public class Boj4179 {
	static char[][] board;
	static int[][] time;
	static int maxX, maxY;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		
		board = new char[maxX][maxY];
		time = new int[maxX][maxY];
		
		Queue<Point> fire = new LinkedList();
		Queue<Point> jihun = new LinkedList();
		// ������ �ʱ�ȭ, �� ������ ã��
		for(int i = 0; i < maxX; i++) {
			char[] boardVal = br.readLine().toCharArray();
			for(int j = 0; j < maxY; j++) {
				board[i][j] = boardVal[j];
				time[i][j] = Integer.MAX_VALUE;
				if(board[i][j] == 'F') {
					fire.add(new Point(i,j));
					time[i][j] = 0;
				} 
				else if(board[i][j] == 'J') {
					jihun.add(new Point(i, j));
					time[i][j] = 0;
				}
			}
		}
		
		// BFS : ��
		while(!fire.isEmpty()) {
			Point cur = fire.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] == '#' || time[nx][ny] != Integer.MAX_VALUE) continue;
				
				fire.add(new Point(nx, ny));
				time[nx][ny] = time[cur.x][cur.y] + 1; 
			}
		}
		
		// BFS : ����
		while(!jihun.isEmpty()) {
			Point cur = jihun.poll();
			
			if(cur.x == 0 || cur.x == maxX-1 || cur.y == 0 || cur.y == maxY-1) {
				System.out.println(time[cur.x][cur.y] + 1);
				return;
			}
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] != '.' || time[nx][ny] <= time[cur.x][cur.y] + 1) continue;
				
				jihun.add(new Point(nx, ny));
				time[nx][ny] = time[cur.x][cur.y] + 1;
				
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
