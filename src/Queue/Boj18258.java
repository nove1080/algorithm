import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ť 2 [S4]

public class Boj18258 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList();
		
		StringTokenizer st;
		int last = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			case "push":
				int val = Integer.parseInt(st.nextToken());
				last = val;
				q.add(val);
				break;
			case "pop":
				sb.append(q.isEmpty() ? -1 : q.poll()).append("\n");
				break;
			case "size":
				sb.append(q.size()).append("\n");
				break;
			case "empty":
				sb.append(q.isEmpty() ? 1 : 0).append("\n");
				break;
			case "front":
				sb.append(q.isEmpty() ? -1 : q.peek()).append("\n");
				break;
			case "back":
				sb.append(q.isEmpty() ? -1 : last).append("\n");
				break;
			} // switch
		} // for
		System.out.println(sb);
	}

}
