// ���ĺ� ���� [B4]

import java.io.*;

public class Boj10808 {
    static int[] ans = new int[26];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] c = br.readLine().toCharArray();
		
		for(int i = 0; i < c.length; i++) {
			ans[c[i] - 'a']++;
		}
		
		for(int a : ans) {			
			sb.append(a).append(' ');
		}
		
		System.out.println(sb);
	}

}
