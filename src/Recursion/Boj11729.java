// 하노이 탑 이동 순서 [S1]
package Recursion;

import java.util.*;
import java.io.*;

public class Boj11729 {
	static StringBuilder sb = new StringBuilder();
	static int cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		hanoi(n, 1, 2, 3);
		
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	static void hanoi(int n, int s, int t, int d) {
		if(n == 0) return;
		hanoi(n-1, s, d, t);
		sb.append(s + " " + d).append("\n");
		cnt++;
		hanoi(n-1, t, s, d);
	}
}
