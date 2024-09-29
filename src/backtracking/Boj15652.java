// N과 M(4)

package backtracking;

import java.io.*;
import java.util.*;

public class Boj15652 {

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
	
	static void backtracking(int last, int idx) {
		//base condition
		if(idx == m) {
			save();
			return;
		}
		
		for(int i = last; i <= n; i++) {
			arr[idx] = i;
			backtracking(i, idx + 1);
		}
	}
	
	static void save() {
		for(int k : arr) {
			sb.append(k).append(" ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(1, 0);
		System.out.println(sb);
	}

}
