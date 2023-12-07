//수 정렬하기 5
package Sort;

import java.io.*;
import java.util.*;

public class Boj15688 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static int[][] count = new int[2][1000001];
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num < 0) { 
				num *= -1;
				count[0][num]++;
			} 
			else {
				count[1][num]++;
			}
		}
		
	}
	
	//counting sort
	static void printResult() {
		for(int i = 0; i < 1000000; i++) {
			int num = 1000000-i;
			if(count[0][num] != 0) {
				while(count[0][num] > 0) {
					sb.append(num*-1).append("\n");
					count[0][num]--;
				}
			}
		}
		
		for(int i = 0; i <= 1000000; i++) {
			if(count[1][i] != 0) {
				while(count[1][i] > 0) {
					sb.append(i).append("\n");
					count[1][i]--;
				}
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		 
		init();
		printResult();
	}

}
