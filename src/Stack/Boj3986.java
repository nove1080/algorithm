// ���� �ܾ� [S4]

// sol
// stack �̿�
//  1. ������ ���� stack�� peek�� ���ؼ� ������ pop()
//  2. �̷����ؼ� stack�� �Ⱥ�� fail

import java.io.*;
import java.util.*;

public class Boj3986 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- != 0) {
			Stack<Character> stk = new Stack();
			char[] arr = br.readLine().toCharArray();
			
			for(char c : arr) {
				if(stk.isEmpty()) {
					stk.add(c);
				} else {
					if(stk.peek() == c) {
						stk.pop();
					} else {
						stk.add(c);
					}
				}
			}
			
			if(stk.isEmpty()) ans++;
		}
		System.out.println(ans);
	}

}
