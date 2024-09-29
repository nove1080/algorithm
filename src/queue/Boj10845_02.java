import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ť [S4]

// Solution
//  1. ������ ť�� �̿�
//  ++ Java���� Queue�� �������̽��θ� �����Ѵ�.
//  ++ ���� Queue�� ������ �ƹ� Ŭ���� (ex : LinkedList)�� ��Ƽ� ����.
//  ++ switch���� �������� ������ ���/���ڿ��� �����ϴ�.
public class Boj10845_02 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		
		StringTokenizer st;
		int last = 0; // ������ ���� ��� (back ����) 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			case "push":
				// q.offer()?????????
				int tmp = Integer.parseInt(st.nextToken());
				q.add(tmp);
				last = tmp;
				break;
			case "pop":
				if(q.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(q.poll()).append("\n");
				}
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				sb.append(q.isEmpty() ? 1 : 0).append("\n");
				break;
			case "front":
				if(q.isEmpty()) {
					sb.append("-1\n");
				} else {					
					sb.append(q.peek()).append("\n");
				}
				break;
			case "back":
				if(q.isEmpty()) {
					sb.append("-1\n");
				} else {					
					sb.append(last).append("\n");
				}
				break;
			} // swtich
		} // for
		System.out.println(sb);
	}

}
