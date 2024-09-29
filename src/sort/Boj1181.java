//단어 정렬
package sort;

import java.util.*;
import java.io.*;

public class Boj1181 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static String[] sArr;
	
	public static void main(String[] args) throws Exception {
		init();
//		Arrays.sort(sArr, (String s1, String s2) -> {
//			if(s1.length() != s2.length()) {
//				return s1.length() - s2.length();
//			}
//			return s1.compareTo(s2);
//		});
		
		Arrays.sort(sArr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length() != s2.length()) {
					return s1.length() - s2.length();
				} else {
					return s1.compareTo(s2);
				}
			}
		});
	}
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		sArr = new String[n];
		
		for(int i = 0; i < n; i++) {
			sArr[i] = br.readLine();
		}
	}
	



}
