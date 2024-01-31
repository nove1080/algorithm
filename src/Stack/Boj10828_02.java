package Stack;
// ���� [S4]
// Stack ��Ű�� Ȱ��
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Boj10828_02 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack();
		StringTokenizer st;
		while(n-- != 0) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if(command.equals("push")) {
				int val = Integer.parseInt(st.nextToken());
				stack.add(val);
			} 
			else if(command.equals("pop")) {
				if(!stack.empty()) {
					sb.append(stack.pop()).append("\n");					
				} else {
					sb.append("-1").append("\n");
				}
			}
			else if(command.equals("size")) {
				sb.append(stack.size()).append("\n");
			}
			else if(command.equals("empty")) {
				sb.append(stack.empty() ? 1 : 0).append("\n");
			}
			else {
				if(!stack.empty()) {
					sb.append(stack.peek()).append("\n");					
				} else {
					sb.append("-1").append("\n");
				}
			} // if
		} // while
		System.out.println(sb);
	}
}