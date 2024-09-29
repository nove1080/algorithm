import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
// AC [G5]

// idea
//  1. R�� D�� �����Ѵ�.
//  2. R - ������
//   2-1. Deque mainDeque, ori, rev;
//   2-2. loop: until !ori.isEmpty()
//			do: rev.addfirst(ori.pollfirst())
//   2-3. mainDeque�� rev�� ����
//  3. D - ù ��° �� ������
//   3-1. mainDeque.pollfirst();


// idea ��
//  �� �ð������� �̸� �����ߴ���� ���� Deque�� �������� Ʋ���ٴ� ���� �� �� �ִ�.
public class Boj5430 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			char[] command = br.readLine().toCharArray();
			int size = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
			Deque<Integer> d = new LinkedList();
			
			// init a deque
			while(st.hasMoreTokens()) {
				d.addLast(Integer.parseInt(st.nextToken()));
			}
			
			// operate commands
			boolean isError = false;
			boolean isReverse = false;
			for(char c : command) {
				switch(c) {
				case'R':
					isReverse = !isReverse;
					break;
				case'D':
					if(d.isEmpty()) {
						sb.append("error\n");
						isError = true;
						break;
					}
					if(isReverse) {
						d.pollLast();
						break;
					}
					d.pollFirst();
					break;
				}
				if(isError) break;
			} // for
			
			if(!isError) {
				sb.append(printDeque(d, isReverse));
			}
		} // for
		System.out.println(sb);
	}
	
	static String printDeque(Deque<Integer> d, boolean isReverse) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(d.size() >= 1) {
			if(isReverse) {
				sb.append(d.pollLast());
				while(!d.isEmpty()) {
					sb.append(",").append(d.pollLast());
				}
			}
			else { // ������
				sb.append(d.pollFirst());
				while(!d.isEmpty()) {
					sb.append(",").append(d.pollFirst());
				}
			}
			
		}
		sb.append("]\n");
		return sb.toString();
	}

}
