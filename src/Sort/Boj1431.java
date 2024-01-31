//시리얼 번호
package Sort;

import java.util.*;
import java.io.*;

public class Boj1431 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n;
	static Serial[] sArr;
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		sArr = new Serial[n];
		
		for(int i = 0; i < n; i++) {
			sArr[i] = new Serial(br.readLine());
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(sArr);
		printArr();
	}
	
	static void printArr() {
		for(Serial s : sArr) {
			sb.append(s.str).append("\n");
		}
		System.out.println(sb);
	}
	
	static class Serial implements Comparable<Serial>{
		String str;
		int sum;
		int size;
		
		public Serial(String str) {
			this.str = str;
			size = str.length();
			sum = 0;
			
			char[] cArr = str.toCharArray();
			
			for(char c : cArr) {
				if(c >= '1' && c <= '9') {
					sum += (c - '0');
				}
			}
			
		}
		
		@Override
		public int compareTo(Serial s) {
			if(size != s.size) 
				return size - s.size;
			else if(sum != s.sum)
				return sum - s.sum;
			else {
				return str.compareTo(s.str);
			}
		}
		
	}

}
