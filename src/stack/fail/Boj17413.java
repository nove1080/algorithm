// �ܾ� ������ 2

import java.io.*;
import java.util.*;

public class Boj17413 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static char[] cArr;
	static boolean[] isTarget;
	
	static void init() throws Exception {
		cArr = br.readLine().toCharArray();
		isTarget = new boolean[cArr.length];
	}
	
	static void reverse(int start, int end) {
		Stack<Character> stack = new Stack();
		for(int i = start; i < end; i++) {
			stack.add(cArr[i]);
		}
		for(int i = start; i < end; i++) {
			cArr[i] = stack.pop();
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		
		// ������ index üũ�ϱ�
		boolean isTag = false;
		for(int i = 0; i < cArr.length; i++) {
			char c = cArr[i];
			
			// tag��� ������ �ʴ´�.
			if(c == '<') isTag = true;
			if(c == '>') {
				isTag = false;
				continue;
			}
			if(isTag) continue;
			if(c == ' ') continue;
			isTarget[i] = true;
		}
		
		// ������ �ǽ�
		int reverseSize = 0;
		for(int i = 0; i < cArr.length; i++) {
			if(!isTarget[i] && reverseSize != 0) {
				int start = i - reverseSize;
				reverse(start, i);
				reverseSize = 0;
			}
			if(isTarget[i]) reverseSize++;
		}
		
		// ������ ������
		if(reverseSize != 0) {
			int start = cArr.length - reverseSize;
			reverse(start, cArr.length);
		}
		
		sb.append(cArr);
		System.out.println(sb);
	}

}
