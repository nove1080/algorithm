//수 정렬하기 4
package sort;

import java.io.*;

public class Boj11931 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static int[] plusCnt = new int[1000005];
	static int[] minusCnt = new int[1000005];
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num < 0) {
				num *= -1;
				minusCnt[num]++;
			} else
				plusCnt[num]++;
		}
	}
	
	static void printArr() {
		for(int i = 1000000; i >= 0; i--) {
			if(plusCnt[i] != 0) {
				sb.append(i).append("\n");
			}
		}
		for(int i = 0; i <= 1000000; i++) {
			if(minusCnt[i] != 0) {
				sb.append(i*-1).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		printArr();
	}

}
