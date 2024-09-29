// N과 M(3)
package backtracking;

import java.io.*;
import java.util.*;

public class Boj15651 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	static int[] arr;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
	}
	
	static void backtracking(int length) {
		// base condition
		if(length == m) {
			for(int k : arr) {
				sb.append(k).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			arr[length] = i + 1;
			backtracking(length + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0);
		System.out.println(sb);
	}

}
