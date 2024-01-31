package Bfs;// ���� ���ϱ� [S1]

// sol
// 1. BFS (���� ���ϱ� + ������ ����)

// ���簢���� �׸��� �˰���
// 1. �̰� �ٽ��ϵ� ��ǥ�� �迭�� �� �̿��غ���
import java.util.*;
import java.io.*;
public class Boj2583 {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int[][] board;
	static boolean[][] vis;
	static int maxX, maxY;
	static int[] moveX = {0,1,0,-1};
	static int[] moveY = {1,0,-1,0};
	static int k;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		maxX = Integer.parseInt(st.nextToken());
		maxY = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		board = new int[maxX][maxY];
		vis = new boolean[maxX][maxY];
		
		System.out.println(bfs());
	}
	
	static String bfs() throws Exception{
		StringBuilder ans = new StringBuilder();
		int cnt = 0;
		int[] area = new int[maxX * maxY];  int areaIndex = -1;
		Arrays.fill(area, 0);
		
		fillBoard();
		Queue<Point> q = new LinkedList();
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				if(board[i][j] == 0 && !vis[i][j]) {
					cnt++; areaIndex++;
					q.add(new Point(i, j));
					vis[i][j] = true;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						area[areaIndex]++;
						
						for(int idx = 0; idx < 4; idx++) {
							int nx = cur.x + moveX[idx];
							int ny = cur.y + moveY[idx];
							
							if(nx < 0 || nx >= maxX || ny < 0 || ny >= maxY) continue;
							if(board[nx][ny] != 0 || vis[nx][ny]) continue;
							
							q.add(new Point(nx, ny));
							vis[nx][ny] = true;
						}
					} // while
				}
			}
		}
		Arrays.sort(area, 0, cnt); // ���簢���� ��ü�� ä��� ���� ����.
		ans.append(cnt).append("\n");
		for(int i = 0; i < cnt; i++) {
			ans.append(area[i]).append(" ");
		}
		return ans.toString();
	}
	
	static void fillBoard() throws Exception{
		while(k-- != 0) {
			int[] tmp = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				tmp[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = tmp[0]; i < tmp[2]; i++) {
				for(int j = tmp[1]; j < tmp[3]; j++) {
					board[j][i]++;
				}
			}
			//printBoard();
		}
	}
	
	static void printBoard() {
		System.out.println("==================");
		for(int i = 0; i < maxX; i++) {
			for(int j = 0; j < maxY; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
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
