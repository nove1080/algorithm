//수 정렬하기 3
package Sort;

import java.io.*;
import java.util.*;

public class Boj10989 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static int[] arr;
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	}
	
	static void printArr() {
		for(int k : arr) {
			sb.append(k + "\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception{
		init();
		Arrays.sort(arr);
		printArr();
	}

}
