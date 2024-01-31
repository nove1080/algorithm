// N-Queen [G4]
package Back_Tracking;

import java.io.*;

public class Boj9663 {
	static int n, ans;
	static boolean isBlockedCol[];
	static int isBlockedDia1[]; // 양수 대각선
	static int isBlockedDia2[]; // 음수 대각선
	
	static void init() {
		isBlockedCol = new boolean[n];
		isBlockedDia1 = new int[n];
		isBlockedDia2 = new int[n];
		
		for(int i = 0; i < n; i++) {
			isBlockedDia1[i] = 9999;
			isBlockedDia2[i] = 9999;
			isBlockedCol[i] = false;
		}
	}
	
	static void recursion(int r) {
		if(r == n) {
			ans++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(isPossible(r, i)) {
				play(r, i);
				recursion(r+1);
				unplay(r, i);
			}
		}
	}
	
	static boolean isPossible(int r, int c) {
		int dia1 = r + c;
		int dia2 = r - c;
		
		if(isBlockedCol[c]) return false;
		for(int i = 0; i < n; i++) {
			if(dia1 == isBlockedDia1[i] || dia2 == isBlockedDia2[i]) return false;
		}
		
		return true;
	}
	
	static void play(int r, int c) {
		isBlockedCol[c] = true;
		isBlockedDia1[r] = r + c;
		isBlockedDia2[r] = r - c;
	}
	
	static void unplay(int r, int c) {
		isBlockedCol[c] = false;
		isBlockedDia1[r] = 9999;
		isBlockedDia2[r] = 9999;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		init();
		recursion(0);
		System.out.println(ans);
	}

}