//로또

package backtracking;

import java.util.*;
import java.io.*;

public class Boj6603 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int k;
	static int[] arr;
	static int[] selectArr;
	static boolean[] isUsed;
	
	static boolean start() throws Exception {
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		
		if(k == 0) return false;
		
		arr = new int[k];
		selectArr = new int[6];
		isUsed = new boolean[k];
		for(int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		return true;
	}
	
	static void backtracking(int idx, int lastIdx) {
		if(idx == 6) {
			save();
			return;
		}
		
		for(int i = lastIdx; i < k; i++) {
			selectArr[idx] = arr[i];
			backtracking(idx + 1, i + 1);
		}
	}
	
	static void save() {
		for(int k : selectArr)
			sb.append(k).append(" ");
		sb.append("\n");
	}
	
	public static void main(String[] args) throws Exception {
		while(start()) {
			backtracking(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
