package Array;// �� ��ȣ [S5]

import java.io.*;

public class Boj1475 {
	static int[] ans = new int[10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 6�� 9�� ���� = (6 + 9 / 2)   +   (6 + 9 % 2)
			
		char[] c = br.readLine().toCharArray();
		
		for(int i = 0; i < c.length; i++) {
			ans[c[i] - '0']++;
		}
		
		int sixOrNine = ((ans[6] + ans[9])/2) + ((ans[6] + ans[9]) % 2);
		
		int max = sixOrNine;
		for(int i = 0; i < 10; i++) {
			if(i == 6 || i == 9) continue;
			max = Math.max(max, ans[i]);
		}
		
		System.out.println(max);
	}	

}
