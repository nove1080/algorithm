// 부분수열의 합
package backtracking;

import java.util.*;
import java.io.*;

public class Boj1182 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, s, ans;
	static int[] arr;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void backtracking(int start, int sum) {
		int next = start + 1;
		
		if(next == n) {
			if(sum == s) ans++;
			return;
		}
		
		// 1. 다음 원소와 합을 넘겨 재귀호출
		backtracking(next, sum + arr[next]);
		// 2. 다음 원소와 합을 하지 않고 재귀호출
		backtracking(next, sum);
	}
	
	public static void main(String[] args) throws Exception{
		init();
		
		for(int i = 0; i < n; i++) {
			backtracking(i, arr[i]);
		}
		
		System.out.println(ans);
	}

}
