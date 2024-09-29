//역원소 정렬

/**
 * 문자열 뒤집기
 *  -> StringBuilder, StringBuffer의 reverse() 사용
 */
package sort;

import java.util.*;
import java.io.*;

public class Boj5648 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int n;
	static long[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(arr);
		printArr();
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new long[n];
		
		int arrIdx = 0;
		while(st.hasMoreTokens()) {
			sb = new StringBuilder(st.nextToken());
			arr[arrIdx++] = Long.parseLong(sb.reverse().toString());
		}
		
		while(arrIdx < n) {
			sb = new StringBuilder(br.readLine());
			st = new StringTokenizer(sb.reverse().toString());
			while(st.hasMoreTokens()) {
				arr[arrIdx++] = Long.parseLong(st.nextToken());
			}
		}
		
	}
	
	static void printArr() {
		sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(arr[i]).append("\n");
		}		
		System.out.println(sb);
	}
	
}
