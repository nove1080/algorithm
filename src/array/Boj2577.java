// ������ ���� [B2]
package array;

import java.io.*;

public class Boj2577 {
	static int[] ans = new int[10];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long a = Long.parseLong(br.readLine());
		long b = Long.parseLong(br.readLine());
		long c = Long.parseLong(br.readLine());
		
		long val = a * b * c;
		
		char[] str = Long.toString(val).toCharArray();
		
		for(int i = 0; i < str.length; i++) {
			ans[str[i] - '0']++;
		}
		
		for(int k : ans) {
			sb.append(k).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
