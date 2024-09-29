package bfs;
// �ٸ� ����� [G3]

// sol
//  1. bfs
//  2. O(n^4) : n = 100 �̹Ƿ� �ð����� ����� ���� ���� -------- (�ٽ�)
//  3. �׷��� Ǯ�� ���ϱ� �� Ǯ�̴� O(n^2) ����....


//  �� �ٸ����� �ٸ��� �մ¹�
//   1. bfs�� �� ������ �ľ��Ѵ�.
//   2. �� ��, vis���� �� ������ �ѹ����� ������ �ʱ�ȭ���ش�.
//   3. ������ ������ (ù ��° bfs) �ٸ��� �մ� bfs�� �����Ѵ�.
//   4. if(�� ������ �ƴϰ� && 0�� �������鼭 && ���ʷ� 1�� ������) -> �ٸ��� ��� -------------- (�ٽ�)
//      if(�� ������ �ƴϴ�(vis������) && �ٸ��� ���� �ּ� 1���� �ȴ�.(board���� 0�� ��ĥ ������ cnt)) 
import java.io.*;
import java.util.*;

public class Boj2146 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[][] board;
	static int[][] area;
	static int[][] dist; // �ٸ��� ���� �� ���
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	static int size;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		size = Integer.parseInt(br.readLine());
		board = new int[size][size];
		dist = new int[size][size];
		area = new int[size][size];
		
		init();
		
		int areaNum = 0;
		// ��� ǥ��
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] == 1 && area[i][j] == 0) {
					bfs(new Point(i, j), ++areaNum);
				}
			}
		}
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] != 0) {
					ans = Math.min(ans, bridge(new Point(i, j)));
				}
			}
		}
		System.out.println(ans);
	}
	
	static void init() throws Exception{
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				area[i][j] = 0;
			}
		}
	}
	
	static void bfs(Point start, int areaNum) {
		Queue<Point> q = new LinkedList();
		
		q.add(start);
		area[start.x][start.y] = areaNum;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
				if(board[nx][ny] != 1 || area[nx][ny] != 0) continue;
				
				q.add(new Point(nx, ny));
				area[nx][ny] = areaNum;
			}
		}
	}
	
	static void resetDist() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	static int bridge(Point s) {
		resetDist();
		Queue<Point> q = new LinkedList();
		q.add(s);
		dist[s.x][s.y] = 1; 
		
		int randNum = area[s.x][s.y];
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
						
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
				// ���� ª�� �ٸ��� ����
				if(area[nx][ny] != 0 && area[nx][ny] != randNum) {
					return dist[cur.x][cur.y] - 1; 
				}
				if(board[nx][ny] != 0 || dist[nx][ny] <= dist[cur.x][cur.y] + 1) continue;
				
				q.add(new Point(nx, ny));
				dist[nx][ny] = dist[cur.x][cur.y] + 1; 
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
