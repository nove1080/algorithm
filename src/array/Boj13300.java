// �� ���� [B2]

import java.io.*;
import java.util.*;

public class Boj13300 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[2][6];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			arr[sex][grade - 1]++;
		}
		
		int ans = 0;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				ans += arr[i][j] / k;
				if(arr[i][j] % k != 0) 
					 ans++;
				
			}
		}
		
		System.out.println(ans);
	}

}
