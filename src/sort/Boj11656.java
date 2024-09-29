//접미사 배열
package sort;

import java.util.*;
import java.io.*;

public class Boj11656 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static String[] sArr;
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(sArr);
		printArr();
	}
	
	public static void init() throws Exception {
		String s = br.readLine();
		sArr = new String[s.length()];
		
		for(int i = 0; i < s.length(); i++) {
			sArr[i] = s.substring(i);
		}
		
	}
	
	public static void printArr() {
		for(int i = 0; i < sArr.length; i++) {
			sb.append(sArr[i]).append("\n");
		}
		System.out.println(sb);
	}

}
