import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// ī��2 [S4]

public class Boj2164 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Deque<Integer> d = new LinkedList();
		for(int i = 1; i <= n; i++) {
			d.addFirst(i);
		}

		while(d.size() != 1) {
			d.pollLast();
			int tmp = d.peekLast();
			d.addFirst(tmp);
			d.pollLast();
		}
		
		for(int i : d) {
			System.out.println(i);
		}
	}
}