// 색종이 만들기[S2]
package recursion;

import java.util.*;
import java.io.*;

public class Boj2630 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int board[][];
	static int n;
	static int white = 0;
	static int blue = 0;
	
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

	static void sol(int row, int col, int size) {
		boolean needcut = false;
		int color = board[row][col];
		
		loop: // 잘라야하는지 검사
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				if(board[i][j] != color) {
					needcut = true;
					break loop;
				}
			}
		}
		
		if(needcut) {
			sol(row			, col + size/2, size/2); // 1사분면
			sol(row			, col		  , size/2); // 2사분면
			sol(row + size/2, col		  , size/2); // 3사분면
			sol(row + size/2, col + size/2, size/2); // 4사분면
		} else {
			if(color == 0) white++;
			else blue++;			
		}
		
		return;
	}
	public static void main(String[] args) throws Exception{
		init();
		sol(0, 0, n);
		System.out.print(white+"\n"+blue);
	}
}
