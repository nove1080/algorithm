// N과 M(2)
package Back_Tracking;

import java.util.*;
import java.io.*;

public class Boj15650 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static int[] arr;
	static boolean[] isUsed;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		isUsed = new boolean[n];
	}
	
	static void backtracking(int num, int length) {
		// base condition
		if(length == m) {
			for(int i = 0; i < length; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// recursion
		for(int i = num; i < n; i++) {
			if(!isUsed[i]) {
				int nextNum = i + 1;
				isUsed[i] = true;
				arr[length] = nextNum;
				backtracking(nextNum, length + 1);
				isUsed[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0, 0);
		System.out.println(sb);
	}

}
