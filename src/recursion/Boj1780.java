// 종이의 개수[S2]
package recursion;

import java.util.*;
import java.io.*;

public class Boj1780 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int minusOne = 0;
	static int zero = 0;
	static int one = 0;
	
	static int board[][];
	static int n;
	
	static void init() throws Exception{
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void partition(int row, int col, int size) {
		int num = board[row][col];
		
		if(test(row, col, size)) {
			if(num == -1) minusOne++;
			else if(num == 0) zero++;
			else one++;
		} 
		else { // 9등분
			int nextSize = size/3;
			partition(row, col, nextSize);
			partition(row, col+nextSize, nextSize);
			partition(row, col+2*nextSize, nextSize);

			partition(row+nextSize, col, nextSize);
			partition(row+nextSize, col+nextSize, nextSize);
			partition(row+nextSize, col+2*nextSize, nextSize);

			partition(row+2*nextSize, col, nextSize);
			partition(row+2*nextSize, col+nextSize, nextSize);
			partition(row+2*nextSize, col+2*nextSize, nextSize);
		}
		return;
	}
	
	// 종이를 안잘라도 되는지 확인
	static boolean test(int row, int col, int size) {
		int num = board[row][col];

		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				if(board[i][j] != num) 
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		init();
		partition(0, 0, n);
		System.out.println(minusOne+"\n"+zero+"\n"+one);
	}

}
