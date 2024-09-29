// 별 찍기 - 10[G5]

package recursion;

import java.io.*;

public class Boj2447 {
	static char[][] board;
	static int n;
	
	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(board[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void partition(int r, int c, int n) {
		if(n > 1) {
			partition(r, c, n/3);
			partition(r, c+n/3, n/3);
			partition(r, c+(2*n/3), n/3);
			
			partition(r+n/3, c, n/3);
			partition(r+n/3, c+(2*n/3), n/3);
			
			partition(r+(2*n/3), c, n/3);
			partition(r+(2*n/3), c+n/3, n/3);
			partition(r+(2*n/3), c+(2*n/3), n/3);
		} else {
			board[r][c] = '*';
		}
	
		return;
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		board = new char[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = ' ';
			}
		}
		partition(0, 0, n);
		printBoard();
	}
}
