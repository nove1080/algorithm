//빈도 정렬
/**
 * List에 map의 key를 담아서 정렬한다. 좋은 아이디어인 듯 하다.
 * map: <num, freq>
 */
package sort;

import java.util.*;
import java.io.*;

public class Boj2910 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, c;
	static int idx = 0;
	static MyNum[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(arr, 0, idx, (MyNum m1, MyNum m2) -> {
			if(m1.count != m2.count) {
				return m2.count - m1.count;
			}
			
			return m1.order - m2.order;
		});
		printArr();
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new MyNum[n];
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			boolean isExist = false;
			int num = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < idx; i++) {
				if(arr[i].num == num) { //이미 존재하는 숫자
					arr[i].count++;
					isExist = true;
					break;
				}
			}
			
			if(!isExist) arr[idx] = new MyNum(num, idx++);
		}
	}
	
	static void printArr() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < idx; i++) {
			while(arr[i].count != 0) {
				sb.append(arr[i].num).append(" ");
				arr[i].count--;
			}
		}
		System.out.println(sb);
	}
	
	static class MyNum {
		public int num;
		public int count = 1;
		public int order;
		
		public MyNum(int num, int order) {
			this.num = num;
			this.order = order;
		}
		
	}
}
