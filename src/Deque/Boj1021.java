import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// ȸ���ϴ� ť [S3]

// solution
//  1. ����� ��ȯ ť = ��
//  2. ���ϰ��� �ϴ� ���Ҹ� �������� ��/�쿡 ��� ���Ұ� �ִ��� �Ǵ� 


// 1 10 3 4 5 6 7 8 
public class Boj1021 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		Deque<Integer> d = new LinkedList();
		for(int i = 1; i <= max; i++) {
			d.offerLast(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			Iterator it2 = d.iterator();
			while(it2.hasNext()) {
				System.out.print(it2.next() + ", ");
			}
			System.out.println();
			Iterator it = d.iterator(); 
			int moveLeft = 0;
			
			while(x != (Integer)it.next()) {
				moveLeft++;
			}
			int moveRight = Math.abs(d.size() - moveLeft - 1);
			
			if(d.size() == 1) {
				System.out.println(ans);
				return;
			}
			
			System.out.print("mL : " + moveLeft);
			System.out.println(", mR : " + moveRight);
			// move Left
			if(moveLeft <= moveRight) {
				System.out.println("====moveLeft====");
				while(x != d.peekFirst()) {
					System.out.println("L");
					int first = d.pollFirst();
					d.offerLast(first);
					ans++;					
				}
				d.pollFirst();
				System.out.println("============");
			} else { // move Right
				System.out.println("====moveRight====");
				while(x != d.peekFirst()) {		
					System.out.println("R");
					int last = d.pollLast();
					d.offerFirst(last);
					ans++;					
				}
				d.pollLast();			
				System.out.println("============");
			}
		} // for
		System.out.println(ans);
	}
}