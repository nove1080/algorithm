package bfs;// ??????? ??? [S1]

// Sol
//  0. ????? -> BFS?
//  1. BFS???? moveX,Y?? ??????? ????????? ????
//  2. time???? ?????? ?????? ???????????? ??????? ???????.
//  3. ??? ?дь ??????? ??? ???????? ???? ??? time?? ??? ????????(????? ????)
import java.io.*;
import java.util.*;

public class Boj7562 {

	static int[] moveX = {-2,-2,-1,-1,1,1,2,2};
	static int[] moveY = {1,-1,2,-2,2,-2,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		while(testCase-- != 0) {
			int size = Integer.parseInt(br.readLine());
			int[][] time = new int[size][size];
			
			// init
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					time[i][j] = Integer.MAX_VALUE;
				}
			}
			
			// ?? 2?? ???
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Point myPos = new Point(x, y);
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			Queue<Point> q = new LinkedList();
			q.add(myPos);
			time[myPos.x][myPos.y] = 0;
			
			while(!q.isEmpty()) {
				Point cur = q.poll();

				for(int i = 0; i < 8; i++) {
					int nx = cur.x + moveX[i];
					int ny = cur.y + moveY[i];
					
					if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
					if(time[nx][ny] <= (time[cur.x][cur.y] + 1)) continue;
					
					time[nx][ny] = time[cur.x][cur.y] + 1; 
					q.add(new Point(nx, ny));
				}
			}
			
			sb.append(time[x][y]).append("\n");
		} // while1
		System.out.println(sb);
	} // main

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
