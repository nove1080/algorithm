// Z [S1]
package Recursion;

import java.util.*;
import java.io.*;

public class Boj1074 {
	
	static int weight(int r, int c, int n) {
		int quadrant = check(r, c, n);
		int quadrantSize = (int) (Math.pow(2, n-1) * Math.pow(2, n-1));
		
		if(quadrant == 2) 
			return 0;
		else if(quadrant == 1) 
			return quadrantSize;
		else if(quadrant == 3) 
			return quadrantSize * 2;
		else 
			return quadrantSize * 3;
		
	}
	
	static int check(int r, int c, int n) {
		if(r >= Math.pow(2, n-1)) {
			if(c >= Math.pow(2, n-1)) 
				return 4;
			else
				return 3;
		}
		else {
			if(c >= Math.pow(2, n-1)) 
				return 1;
			else
				return 2;
		}
	}
	
	static int partition(int r, int c, int n) {
		if(n == 1) {
			return weight(r, c, n);
		}
		else {
			int newR = (int) (r - Math.pow(2, n-1));
			int newC = (int) (c - Math.pow(2, n-1));
			
			if(newR < 0) newR = r;
			if(newC < 0) newC = c;
			
			return partition(newR, newC, n-1) + weight(r, c, n);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(partition(r, c, n));
	}
}
