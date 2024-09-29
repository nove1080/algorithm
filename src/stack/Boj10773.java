package stack;
// ���� [S4]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj10773 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack();
		int ans = 0;
		while(n-- != 0) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {
				stack.pop();
			} else {
				stack.add(x);
			}
		}
		
		for(int k : stack) {
			ans += k;
		}
		
		System.out.println(ans);
	}

}
