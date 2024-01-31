package Bfs.fail;
// �� �μ��� �̵��ϱ� [G3]

// sol
//  1. BFS
//  2. point�� ���� �ν� ������ ������� bfs�� �����Ѵ�.
//  3. ���� ���� ������ point�� field�� ���� �ν����� �ִ��� üũ

import java.io.*;
import java.util.*;

public class Boj_2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
	static int[][] board;
	static int[][] dist;
	static int maxX, maxY;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		
		board = new int[maxX][maxY];
		dist = new int[maxX][maxY];
		init();
		
		System.out.println(bfs());
		printDist();
	}
	
	static void printDist() {
		System.out.println("printDist ======== ");
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===================");
	}
	
	static void init() throws Exception{
		for(int i = 0; i < maxX; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < maxY; j++) {
				board[i][j] = tmp[j] - '0'; 
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	static int bfs() {
		Queue<Point> q = new LinkedList();
		q.add(new Point(0,0));
		dist[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.x == maxX -1 && cur.y == maxY-1) return dist[cur.x][cur.y];
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				//  �� �߸��� ���� �νð� �����Ѱ�� �ð��� ������ �����־� ���Ŀ� ������ ���� �� �ִ�.
				if(dist[nx][ny] < dist[cur.x][cur.y] + 1) continue;
				if(board[nx][ny] == 1 && !cur.isCrush) {
					q.add(new Point(nx, ny, true));
					dist[nx][ny] = dist[cur.x][cur.y] + 1;
					continue;
				} else if(board[nx][ny] == 0) {
					q.add(new Point(nx, ny, cur.isCrush));
					dist[nx][ny] = dist[cur.x][cur.y] + 1;
				}
			}
		}	
		return -1;
	}
}
class Point{
	int x, y;
	boolean isCrush;
	
	Point(int x, int y){
		this(x, y, false);
	}
	
	Point(int x, int y, boolean isCrush){
		this.x = x;
		this.y = y;
		this.isCrush = isCrush;
	}
}

// �ݷ�
/*
 * 
 * 
2 4
0111
0010

2 4
0111
0110

ans: -1

1 1
0

ans : 1

8 8
01000100
01010100
01010100
01010100
01010100
01010100
01010100
00010100

4 4
0101
0101
0001
1110

3 6
010000
010111
000110
 */
