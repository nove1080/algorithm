import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ť [S4]

// Solution
//  1. ���� ť�� �����Ѵ�.

public class Boj10845_01 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		MyQueue q = new MyQueue(10005);
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			 String command = st.nextToken();
			 if(command.equals("push")) {
				 q.push(Integer.parseInt(st.nextToken()));
			 } 
			 else if(command.equals("pop")) {
				 sb.append(q.pop()).append("\n");
			 }
			 else if(command.equals("size")) {
				 sb.append(q.size()).append("\n");
			 }
			 else if(command.equals("empty")) {
				 sb.append(q.isEmpty() ? 1 : 0).append("\n");
			 }
			 else if(command.equals("front")) {
				 sb.append(q.front()).append("\n");
			 }
			 else if(command.equals("back")) {
				 sb.append(q.back()).append("\n");
			 }
		}
		System.out.println(sb);
	}

	static class MyQueue {
		int[] queue;
		int size;
		int head; // �� ��
		int tail; // ���� ��

		MyQueue(int size){
			this.size = size;
			queue = new int[size];
			head = 0;
			tail = 0;
		}

		boolean isEmpty() {
			return head == tail;
		}

		void push(int x) {
			queue[tail++] = x;
		}

		int pop() {
			if(isEmpty())
				return -1;
			return queue[head++];
		}

		int size() {
			return tail - head;
		}

		int front() {
			if(!isEmpty())
				return queue[head];
			return -1;
		}

		int back() {
			if(!isEmpty())
				return queue[tail - 1];
			return -1;
		}
	}

}
