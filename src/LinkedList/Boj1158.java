// �似Ǫ�� ���� [S4]
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


public class Boj1158 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] ans = new int[n];
		
		LinkedList<Integer> list = new LinkedList();
		
		for(int i = 1; i <= n; i++) 
			list.add(i);
		
		ListIterator<Integer> lit = list.listIterator();
		
		sb.append("<");
		while(!list.isEmpty()) {
			int val = 0;
			for(int i = 0; i < k; i++) {
				if(!lit.hasNext()) {
					lit = list.listIterator();
					val = lit.next();
				} else {
					val = lit.next();
				}
			}
			sb.append(val).append(", ");
			lit.remove();
		}
		sb.append(">");
		sb.delete(sb.lastIndexOf(", "), sb.lastIndexOf(">"));
		System.out.println(sb);
	}

}
