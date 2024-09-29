// �踷��� [S2]

// sol
// 1. Stack�� ��ȣ�� ����
// 2. Stack�� �����ϴ� ��Ģ
//  2-1. ���� ��ȣ -> stack�� add & '('��++;
//  2-2. �ݴ� ��ȣ -> 
//   2-2-1. if : stack.top() == '(' -> ������ 
//          do : stack�� �տ��� '('�� ���� - 1 ��ŭ ans�� plus
//   2-2-2. if : stack.top() != '(' -> ������� ��
//          do : ans++ & '('��--;

// ��� : �������� �տ� ���� ��ȣ�� ����ŭ ���ϰ� �ݴ� ������ + 1
import java.util.*;
import java.io.*;

public class Boj10799 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		int stick = 0;
		Stack<Character> stk = new Stack();
		
		char[] input = br.readLine().toCharArray();
		
		
		for(char c : input) {
			if(c == '(') {
				stk.add(c);
				stick++;
			} 
			else { // c == ')'
				stick--;
				if(stk.peek() == '(') { // lazer
					ans += stick;
					stk.add(c);
				}
				else { 					// stick
					ans++;
					stk.add(c);
				}
			}
		}
		System.out.println(ans);
	}

}
