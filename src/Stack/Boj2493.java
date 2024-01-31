package Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// ž [G5]

// Top Ŭ������ ���� ����� 2���� ������ �Ȼ���ص� �ȴ�. (Good)

//class top {
//	int height;
//	int num;
//	
//	public top(int h, int n) {
//		num = n;
//		height = h;
//	}
//}

// 1ȸ�� Ǯ��
// Stack�� ���Ҹ� ä��� ���ÿ� �տ� ������ ū Ÿ���� �ִ��� Ȯ���ϸ� ����

public class Boj2493 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n || st.hasMoreTokens(); i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack();
		Stack<Integer> idxStack = new Stack();
		
		for(int i = 0; i < n; i++) {
			if(stack.empty()) {
				sb.append("0 ");
				stack.push(arr[i]);	  idxStack.push(i+1);
			} 
			else if(stack.peek() >= arr[i]) {
				sb.append(idxStack.peek()).append(" ");
				stack.push(arr[i]);	  idxStack.push(i+1);
			} 
			else { // stack.peek() < arr[i]
				while(!stack.empty() && stack.peek() < arr[i]) {
					stack.pop();   idxStack.pop();
				}
				if(stack.empty()) {
					sb.append("0 ");
					stack.push(arr[i]);	  idxStack.push(i+1);
				}
				else { // stack.peek() >= arr[i]
					sb.append(idxStack.peek()).append(" ");
					stack.push(arr[i]);	  idxStack.push(i+1);
				}
			}
		} // for
		
		System.out.println(sb);
	}

}
