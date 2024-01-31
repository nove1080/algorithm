// ������ȣ���̱� [S1]

// sol
//  1. �⺻ bfs ���� (������ ����, ������ ���� ����)

import java.util.*;
import java.io.*;

public class Boj2667 {
	static int[][] board;
	static boolean[][] vis;
	static int maxSize;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		maxSize = Integer.parseInt(br.readLine());
		
		board = new int[maxSize][maxSize];
		vis = new boolean[maxSize][maxSize];
		
		// init
		for(int i = 0; i < maxSize; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < maxSize; j++) {
				board[i][j] = tmp[j] - '0';
				vis[i][j] = false;
			}
		}
		
		// bfs
		int[] areaArr = new int[maxSize*maxSize];
		int cnt = 0;
		for(int i = 0; i < maxSize; i++) {
			for(int j = 0; j < maxSize; j++) {
				if(board[i][j] == 1 && !vis[i][j]) {
					areaArr[cnt++] = bfs(new Point(i, j));
				}
			}
		}
		
		// print answer
		Arrays.sort(areaArr, 0, cnt);
		sb.append(cnt).append("\n");
		for(int i = 0; i < cnt; i++) {
			sb.append(areaArr[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static int bfs(Point start) {
		Queue<Point> q = new LinkedList();
		q.add(start);
		vis[start.x][start.y] = true; 
		
		int area = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			area++;
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxSize || ny < 0 || ny >= maxSize) continue;
				if(board[nx][ny] == 0 || vis[nx][ny]) continue;
				
				q.add(new Point(nx, ny));
				vis[nx][ny] = true;
			}
		}
		
		return area;
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
