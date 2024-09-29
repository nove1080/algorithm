// N과 M(1) [S3]
package backtracking;

import java.util.*;
import java.io.*;

public class Boj15649 {
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static int arr[];
	static boolean isUsed[];
	
	
	static void init() {
		arr = new int[m];
		isUsed = new boolean[n];
	}
	
	static void solve(int x) {
		if(x == m) {
			save(arr);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(isUsed[i] == true) continue;
			
			arr[x] = i + 1;
			isUsed[i] = true;
			solve(x+1);
			isUsed[i] = false;
		}
	}
	
	static void save(int[] arr) {
		for(int k : arr) {
			sb.append(k + " ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		init();
		solve(0);
		System.out.println(sb);
	}

}
