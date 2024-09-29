//먹을 것인가 먹힐 것인가

/*
 * 시간복잡도 O(n^2) > 1초
 * 
 * 1. A와 B를 크기 순 오름차순 정렬
 * 2. A의 크기가 작은 것부터 B와 비교하고 
 *    결과를 boolean[]에 기록 그리고 몇 쌍이 나오는지 전역변수(ans)로 저장해둠
 * 3. A의 다음 인덱스 vs B의 원소들
 *    이때, boolean에 0이라고 써있는 인덱스랑만 비교한다.
 * 4. boolean에 추가로 1로 변경된 수량 만큼 ans += ans + 추가량
 * 5. A의 마지막 인덱스까지 반복수행
 */
package sort;

import java.util.*;
import java.io.*;

public class Boj7795 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int testcase;
	static int[] a, b;
	static boolean[] check;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		execute();
	}
	
	static void execute() throws Exception {
		testcase = Integer.parseInt(br.readLine());
		for(int i = 0; i < testcase; i++) {
			st = new StringTokenizer(br.readLine());
			int aSize = Integer.parseInt(st.nextToken());
			int bSize = Integer.parseInt(st.nextToken());
			
			a = new int[aSize];
			b = new int[bSize];
			
			int idx = 0;
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
			}
		}
	}
	
}
