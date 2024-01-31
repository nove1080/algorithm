package Back_Tracking;

import java.io.*;
import java.util.*;

public class Boj1759 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static char[] input;
	static char[] code;
	static int aeiouCount;
	static int inputSize, codeSize;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		codeSize = Integer.parseInt(st.nextToken());
		inputSize = Integer.parseInt(st.nextToken());
		input = new char[inputSize];
		code = new char[codeSize];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < inputSize; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
	}
	
	static void backtracking(int idx, int lastIdx) {
		//base condition
		if(idx == codeSize) {
			if(isValid())
				sb.append(code).append("\n");
			return;
		}
		
		for(int i = lastIdx + 1; i < inputSize; i++) {
			char c = input[i];
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') 
				aeiouCount++;
			
			code[idx] = c;
			backtracking(idx + 1, i);
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') 
				aeiouCount--;
		}
	}
	
	static boolean isValid() {
		if(aeiouCount < 1 || codeSize - aeiouCount < 2) return false;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0, -1);
		System.out.println(sb);
	}

}
