//카드

/**
 * 정렬 -> 큰 수부터 카운팅 -> 카운팅이 같으면 신규 숫자로 교체 -> 출력
 * 
 * 시간복잡도 : 정렬(nlgn) + 카운팅(n) < 1초
 */
package Sort;

import java.util.*;
import java.io.*;

public class Boj11652 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int n;
	static long[] arr;
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(arr);
		printAnswer();
	}
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		arr = new long[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
	}
	
	static void printAnswer() {
		long prev = Long.MAX_VALUE;
		long answer = 0;
		int maxCnt = 0;
		int cnt = 0;
		for(int i = n-1; i >= 0; i--) {
			if(prev == arr[i]) {
				cnt++;
			} else {
				prev = arr[i];
				cnt = 1;
			}
			
			if(maxCnt <= cnt) {
				maxCnt = cnt;
				answer = arr[i];
			}
		}
		
		System.out.println(answer);
		
	}
}
