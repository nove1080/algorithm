package Stack;
// ���� ���� [S2]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj1874 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack();
		
		int val = 1;
		for(int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if(val <= input) {
				while(val <= input) {
					stack.add(val++);
					sb.append("+").append("\n");					
				}
			} // if
			
			if(val > input && !stack.isEmpty()) {
				int tmp = stack.pop();
				if(tmp == input) {
					sb.append("-").append("\n");
				} else {
					System.out.println("NO");
					return;
				}
			} // if
		} // for
		System.out.println(sb);
	}

}
