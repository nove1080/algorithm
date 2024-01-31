// ������ [S2]

import java.io.*;
import java.util.*;

public class Boj1406 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Character> list = new LinkedList();
		
		char[] tmp = br.readLine().toCharArray();
		
		// �ʱ� �Է�
		for(char c : tmp) {
			list.add(c);
		}
		
		int n = Integer.parseInt(br.readLine());
		
		ListIterator it = list.listIterator();
		while(it.hasNext()) {
			it.next();
		}
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0); // charAt = casting
			
			switch(command) {
			case 'L':
				if(it.hasPrevious())
					it.previous();
				break;
			case 'D':
				if(it.hasNext())
					it.next();
				break;
			case 'B':
				if(it.hasPrevious()) {
					it.previous();
					it.remove(); // here
				}
				break;
			case 'P':
				char c = st.nextToken().charAt(0);
				it.add(Character.valueOf(c));

				break;
			}
		}
		
		
		for(char c : list) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}

/*
 * [Ǯ��]
 * 	LinkedList & ListIterator�� ����
 */


