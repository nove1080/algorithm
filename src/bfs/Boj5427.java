package bfs;// �� [G4]

// sol
//  1. BFS
//  2. ���� ���� ���Ľ�Ų�� (���� �ð��� ǥ���ϸ�)
//  3. ���� �̵��� �� ���� ���� �����ϸ� �������� ����.
//  4. �׿ܴ� ������ ���ִ� �� bfs�� ����

// Ż�� ����
//  1. ���� ������ ���� �������� ���� �Ͽ��� Ż��!
import java.util.*;
import java.io.*;
public class Boj5427 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		int n = Integer.parseInt(br.readLine());
		
		while(n-- != 0) {
			sb.append(bfs()).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static String bfs() throws Exception{
		st = new StringTokenizer(br.readLine());
		int maxY = Integer.parseInt(st.nextToken());
		int maxX = Integer.parseInt(st.nextToken());
		char[][] board = new char[maxX][maxY];
		int[][] time = new int[maxX][maxY];
		
		Queue<Point> fireQ = new LinkedList();
		Queue<Point> myQ = new LinkedList();
		for(int i = 0; i < maxX; i++) {
			char[] boardVal = br.readLine().toCharArray();
			for(int j = 0; j < maxY; j++) {
				board[i][j] = boardVal[j];
				time[i][j] = Integer.MAX_VALUE;
				// Queue�� ������ ����
				if(board[i][j] == '*') {
					fireQ.add(new Point(i, j));
					time[i][j] = 0;
				} else if(board[i][j] == '@') {
					myQ.add(new Point(i, j));
					time[i][j] = 0;
				}
			}
		}
		// bfs : fire
		while(!fireQ.isEmpty()) {
			Point cur = fireQ.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] != '.' || time[nx][ny] != Integer.MAX_VALUE) continue;
				
				fireQ.add(new Point(nx, ny));
				time[nx][ny] = time[cur.x][cur.y] + 1; 
			}
		}

		// bfs : Me
		while(!myQ.isEmpty()) {
			Point cur = myQ.poll();
			
			// �������� : Ż��
			if(cur.x == 0 || cur.x == maxX - 1 || cur.y == 0 || cur.y == maxY - 1)  
				return String.valueOf(time[cur.x][cur.y] + 1);
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + moveX[i];
				int ny = cur.y + moveY[i];
				
				if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
				if(board[nx][ny] != '.' || time[nx][ny] <= time[cur.x][cur.y] + 1) continue;
				
				myQ.add(new Point(nx, ny));
				time[nx][ny] = time[cur.x][cur.y] + 1; 
			}
		}
		return "IMPOSSIBLE";
	}

	static class Point {
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}
