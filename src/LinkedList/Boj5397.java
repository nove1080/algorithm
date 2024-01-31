// Ű�ΰ� [S2]

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Boj5397 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- != 0) {
			LinkedList<Character> res = new LinkedList();
			
			char[] str = br.readLine().toCharArray();
			
			ListIterator reslit = res.listIterator();
			for(int i = 0; i < str.length; i++) {
				
				switch(str[i]) {
				case '<':
					if(reslit.hasPrevious())
						reslit.previous();
					break;
				case '>':
					if(reslit.hasNext())
						reslit.next();
					break;
				case '-':
					if(reslit.hasPrevious()) {
						reslit.previous();
						reslit.remove();
					}
					break;
				default:
					reslit.add(str[i]);
					continue;
				} // switch
			} // inner while
			Iterator it = res.iterator();
			while(it.hasNext()) {
				sb.append(it.next());
			}
			sb.append("\n");
		} // outter while
		System.out.println(sb);
	}
}

/*
 * [Ǯ��]
 * 	boj_1406�� ���� ����
 *  import ���� �ð��� �ִ°� ����?
 * 
 */