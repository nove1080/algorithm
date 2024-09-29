//좌표 정렬하기 2

package sort;

import java.util.*;
import java.io.*;

public class Boj11651 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n;
	static Ppoint[] pArr;
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		pArr = new Ppoint[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pArr[i] = new Ppoint(x, y);
		}
	}
	
	static void printArr() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(pArr[i].x).append(" ").append(pArr[i].y).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(pArr);
		printArr();
	}
	
	static class Ppoint implements Comparable<Ppoint>{
		int x, y;
		
		Ppoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Ppoint p) {
			if(y == p.y) {
				return x - p.x;
			}
			return y - p.y;
		}
	}

}
