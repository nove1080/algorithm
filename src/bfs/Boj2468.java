package bfs;// ���� ����[S1]

// sol
//  1. BFS (������ ������ �ִ��� �� ������ ã��)
//  2. board���� min ~ max value���� ��ȸ�ϸ鼭 ������ count����.

import java.util.*;
import java.io.*;

public class Boj2468 {
	static int[][] board;
	static boolean[][] vis;
	static int size;
	static int[] moveX = {1,0,-1,0};
	static int[] moveY = {0,1,0,-1};
	
	static int maxHeight = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		vis = new boolean[size][size];
		
		// init
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());

				if(maxHeight < board[i][j]) maxHeight = board[i][j];
			}
		}
		
		// maxHeight �� ���� bfs �� �ʿ䰡 ����
		int ans = 0;
		for(int i = 0; i < maxHeight; i++) {
			initVis();
			int a = bfs(i);
			ans = Math.max(ans, a);
		}
		System.out.println(ans);
	}
	
	static int bfs(int rain) {
		int cnt = 0;
		Queue<Point> q = new LinkedList();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] > rain && !vis[i][j]) {
					cnt++;
					q.add(new Point(i, j));
					vis[i][j] = true;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int idx = 0; idx < 4; idx++) {
							int nx = cur.x + moveX[idx];
							int ny = cur.y + moveY[idx];
							
							if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
							if(board[nx][ny] <= rain || vis[nx][ny]) continue;
							
							q.add(new Point(nx, ny));
							vis[nx][ny] = true;
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	static void initVis() {
		for(int i = 0; i < size; i++) {
			Arrays.fill(vis[i], false);
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
