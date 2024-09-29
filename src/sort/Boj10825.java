//국영수
package sort;

import java.io.*;
import java.util.*;

public class Boj10825 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static Score[] sArr;
	
	public static void main(String[] args) throws Exception {
		init();
		Arrays.sort(sArr);
		printArr();
	}	

	public static void init() throws Exception {
		int n = Integer.parseInt(br.readLine());
		sArr = new Score[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			
			sArr[i] = new Score(name, kor, eng, math);
		}
	}
	
	static void printArr() {
		StringBuilder sb = new StringBuilder();
		
		for(Score s : sArr) {
			sb.append(s.name).append("\n");
		}
		System.out.println(sb);
	}
	
	static class Score implements Comparable<Score> {
		String name;
		int kor, eng, math;
		
		public Score(String name, int kor, int eng, int math) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
		
		@Override
		public int compareTo(Score s) {
			if(kor != s.kor) return s.kor - kor;
			else if(eng != s.eng) return eng - s.eng;
			else if(math != s.math) return s.math - math;
			else return name.compareTo(s.name);
		}
	}
}
