// ��ȣ [S4]

// sol
//

import java.util.*;
import java.io.*;

public class Boj9012 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- != 0) {
			Stack<Character> stk = new Stack();
			char[] arr = br.readLine().toCharArray();
			
			TEST:{
				for(char c : arr) {
					if(c == '(') stk.add(c);
					else {
						if(stk.isEmpty()) {
							sb.append("NO\n");
							break TEST;
						}
						stk.pop();
					}
				}
				sb.append(stk.isEmpty() ? "YES\n" : "NO\n");
			}
		}
		System.out.println(sb);
	}

}
