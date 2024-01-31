// �������� ���� [S4]

// sol

// stack�� Ȱ��
//  
//  2. char[] = br.readLine().toCharArray();
//  3. stack�� ���� ��ȣ�� ���´ٸ� char[] 1���� ����
//   3-1. ��ȿ�� �˻�
//   3-2. if: �ݴ� ��ȣ
//   3-3.  stack.poll -> �˸´� ���� ��ȣ�� keep going
//   3-4.  if �����.
//   3-5.  if �ٸ� ���� ��ȣ
//   3-6.   3-4�� 3-5�� ������!
//  4. stack�� ���� �ʾҴٸ� ������!
import java.io.*;
import java.util.*;
public class Boj4949 {

	static boolean isBalance(char[] arr) {
		Stack<Character> stack = new Stack();
		
		for(char c : arr) {
			if(c == '.') break;
			if(c == '(' || c == '[') {
				stack.add(c);
			} 
			else if(c == ')') {
				if(stack.isEmpty() || stack.pop() != '(') 
					return false;
			} 
			else if(c == ']') {
				if(stack.isEmpty() || stack.pop() != '[')
					return false;
			}
		}
		return stack.isEmpty() ? true : false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = "";
		while(!((str = br.readLine()).equals("."))) {
			char[] arr = str.toCharArray();
			if(isBalance(arr))
				sb.append("yes\n");
			else 
				sb.append("no\n");
		}
		
		System.out.println(sb);
	}

}
