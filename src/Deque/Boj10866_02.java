import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// �� [S4]

// solution
//  1. Deque - ���̺귯�� ���

public class Boj10866_02 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Deque<Integer> d = new LinkedList();
		StringTokenizer st;
		int last = 0; // ������ ���� ��� (back ����) 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			case "push_front":
				int x = Integer.parseInt(st.nextToken());
				d.addFirst(x);
				break;
			case "push_back":
				int y = Integer.parseInt(st.nextToken());
				d.addLast(y);
				break;
			case "pop_front":
				sb.append(d.isEmpty() ? -1 : d.pollFirst()).append("\n");
				break;
			case "pop_back":
				sb.append(d.isEmpty() ? -1 : d.pollLast()).append("\n");
				break;			
			case "size":
				sb.append(d.size()).append("\n");
				break;
			case "empty":
				sb.append(d.isEmpty() ? 1 : 0).append("\n");
				break;
			case "front":
				sb.append(d.isEmpty() ? -1 : d.peekFirst()).append("\n");
				break;
			case "back":
				sb.append(d.isEmpty() ? -1 : d.peekLast()).append("\n");
				break;
			} // swtich
		} // for
		
		System.out.println(sb);
	}

}
