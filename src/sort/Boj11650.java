//좌표 정렬하기

/**
 * Comparable 구현하기
 */

package sort;

import java.util.*;
import java.io.*;

public class Boj11650 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n; //점의 개수
	static Point[] pArr;
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		
		pArr = new Point[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pArr[i] = new Point(x, y);
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
	
	static class Point implements Comparable<Point>{
		int x, y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
	}
}
