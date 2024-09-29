// �� ���� �� [S3]

import java.util.*;
import java.io.*;

public class Boj3273 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int ans = 0;
		for(int a : arr) {
			if(Arrays.binarySearch(arr, x - a) >= 0) 
				ans++;
		}
		
		System.out.println(ans / 2);
	}

}

/* 
 * [Ǯ��]
 * 
 * 	1. 2�� for�� -> �ð��ʰ�
 * 	2. �ٸ� Ž�� ��� -> ���� Ž�� O(nlogn)
 * 	3. Arrays.binarySearch(int[], int); �� �̿�
 * 
 */
