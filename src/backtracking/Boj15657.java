// N과 M (8)
package backtracking;

import java.io.*;
import java.util.*;

public class Boj15657 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	static int[] arr;
	static int[] selectArr;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		selectArr = new int[m];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	}
	
	static void backtracking(int idx, int lastNumIdx) {
		//base condition
		if(idx == m) {
			save();
			return;
		}
		
		for(int i = lastNumIdx; i < n; i++) {
			selectArr[idx] = arr[i];
			backtracking(idx + 1, i);
		}
	}
	
	static void save() {
		for(int k : selectArr) {
			sb.append(k).append(" ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0, 0);
		System.out.println(sb);
	}

}
