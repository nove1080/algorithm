package array;

import java.io.*;

public class Boj1919 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int[] cnt = new int[26];
		
		for(char c : str1) 
			cnt[c-'a']++;
		
		for(char c : str2)
			cnt[c-'a']--;
		
		int ans = 0;
		for(int a : cnt) {
			ans += Math.abs(a);
		}
		System.out.println(ans);
		
	}

}
