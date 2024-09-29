// N과 M(5)

package backtracking;

import java.io.*;
import java.util.*;

public class Boj15654 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	static int[] arr;
	static int[] subArr;
	static boolean[] isUsed;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		isUsed = new boolean[n];
		arr = new int[n];
		subArr = new int[m];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	}
	
	static void backtracking(int idx) {
		//base condition
		if(idx == m) {
			save();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				subArr[idx] = arr[i];
				backtracking(idx + 1);
				isUsed[i] = false;
			}
		}
	}
	
	static void save() {
		for(int k : subArr) {
			sb.append(k).append(" ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0);
		System.out.println(sb);
	}

}
