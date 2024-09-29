// 쿼드트리[S1]
package recursion;

import java.util.*;
import java.io.*;

public class Boj1992 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int board[][];
	static int n;
	static StringBuilder ans = new StringBuilder();
	
	static void init() throws Exception{
		st = new StringTokenizer(br.readLine());		
		n = Integer.parseInt(st.nextToken());
		
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			char[] cArr = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				board[i][j] = cArr[j] - '0';
			}
		}
	}
	static int getColor(int r, int c, int n) {
		int color = board[r][c];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[r+i][c+j] != color) return -1;
			}
		}
		
		return color;
	}
	static void partition(int r, int c, int n) {
		int color = getColor(r, c, n);
		if(color == -1) {
			ans.append("(");
			partition(r, c, n/2);
			partition(r, c+n/2, n/2);
			partition(r+n/2, c, n/2);
			partition(r+n/2, c+n/2, n/2);
			ans.append(")");
		}
		else 
			ans.append(color);
		return;
	}
	
	public static void main(String[] args) throws Exception{
		init();
		partition(0, 0, n);
		System.out.println(ans);
	}

}
