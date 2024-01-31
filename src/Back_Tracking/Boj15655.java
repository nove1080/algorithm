// N과 M (6)
package Back_Tracking;

import java.io.*;
import java.util.*;

public class Boj15655 {
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
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		isUsed = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
	}
	
	static void backtracking(int idx, int lastNum) {
		//base condition
		if(idx == m) {
			save();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!isUsed[i] && arr[i] > lastNum) {
				isUsed[i] = true;
				backtracking(idx + 1, arr[i]);
				isUsed[i] = false;
			}
		}
	}
	
	static void save() {
		for(int i = 0; i < n; i++) {
			if(isUsed[i])
				sb.append(arr[i]).append(" ");
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0, 0);
		System.out.println(sb);
	}

}
