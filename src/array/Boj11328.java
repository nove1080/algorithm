// Strfry [B2]

import java.io.*;
import java.util.*;
public class Boj11328 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] str1 = st.nextToken().toCharArray();
			char[] str2 = st.nextToken().toCharArray();
			int[] cnt = new int[26];
			
			if(str1.length != str2.length) {
				sb.append("Impossible\n");
				continue;
			}
			
			for(int j = 0; j < str1.length; j++) {
				cnt[str1[j] - 'a']++;
				cnt[str2[j] - 'a']--;
			}
			boolean isSame = true;
			
			for(int a : cnt) {
				if(a != 0) {
					isSame = false; 
					break;
				}
			}
			
			if(isSame) {
				sb.append("Possible\n");
			} else {
				sb.append("Impossible\n");
			}
		}
		
		System.out.println(sb);
	}
}

/*
 * Arrays.equals(arr1, arr2); // arr1�� arr2�� ���빰�� ������ ���Ѵ�.
 */
