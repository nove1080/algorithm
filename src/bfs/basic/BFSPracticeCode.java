package bfs.basic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSPracticeCode {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int maxR;
	static int maxC;

	static int[][] board;
	static int[][] visited;

	static int[] moveR = {1,0,-1,0};
	static int[] moveC = {0,1,0,-1};

	static int startR;
	static int startC;

	public static void main(String[] args) throws Exception{
		init();
		print2DIntArray(board);

		System.out.println("PRINT BFS RESULT");
		bfs(startR, startC);
		print2DIntArray(visited);

		init2DArray();

		System.out.println();
		System.out.println();
		System.out.println("PRINT DFS RESULT");
		dfs(startR, startC);
		print2DIntArray(visited);
	}

	private static void init() throws Exception {
		input2DArraySize();
		init2DArray();
		initStartPoint();
	}

	private static void init2DArray() {
		board = new int[maxR][maxC];
		visited = new int[maxR][maxC];
	}

	private static void input2DArraySize() throws IOException {
		System.out.print("공백으로 구분하여 행렬의 크기를 입력해주세요. (예시: 5 5): ");
		String[] input = br.readLine().split(" ");

		maxR = Integer.parseInt(input[0]);
		maxC = Integer.parseInt(input[1]);
	}

	private static void initStartPoint() throws IOException {
		String[] input;
		System.out.println();
		System.out.print("BFS 탐색을 시작할 시작위치를 공백으로 구분하여 입력해주세요.(예시: 2 2): ");
		input = br.readLine().split(" ");
		startR = Integer.parseInt(input[0]);
		startC = Integer.parseInt(input[1]);
	}

	private static void print2DIntArray(int[][] board) {
		System.out.println("=====PRINT 2D Array=====");
		for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxC; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void bfs(int startR, int startC) {
		Queue<Point> q = new LinkedList();
		visited[startR][startC] = 1;
		q.add(new Point(startR, startC, 0));

		while(!q.isEmpty()) {
			Point cur = q.poll();

			for(int dir = 0; dir < 4; dir++) {
				int nr = cur.r + moveR[dir];
				int nc = cur.c + moveC[dir];

				if(nr < 0 || nr >= maxR || nc < 0 || nc >= maxC) continue;
				if(visited[nr][nc] != 0 || board[nr][nc] != 0) continue;

				visited[nr][nc] = cur.depth + 1;
				q.add(new Point(nr, nc, cur.depth + 1));
			}
		}
	}

	private static void dfs(int startR, int startC) {
		Stack<Point> stk = new Stack<>();
		visited[startR][startC] = 1;
		stk.add(new Point(startR, startC, 0));

		while(!stk.isEmpty()) {
			Point cur = stk.pop();

			for(int dir = 0; dir < 4; dir++) {
				int nr = cur.r + moveR[dir];
				int nc = cur.c + moveC[dir];

				if(nr < 0 || nr >= maxR || nc < 0 || nc >= maxC) continue;
				if(visited[nr][nc] != 0 || board[nr][nc] != 0) continue;

				visited[nr][nc] = cur.depth + 1;
				stk.add(new Point(nr, nc, cur.depth + 1));
			}
		}
	}

	static class Point {
		int r, c, depth;

		Point(int r, int c, int depth){
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}
}
