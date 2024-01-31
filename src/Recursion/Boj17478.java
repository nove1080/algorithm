// 재귀함수가 뭔가요? [S5]
package Recursion;

import java.util.*;
import java.io.*;

public class Boj17478 {
	static StringBuilder sb = new StringBuilder();
	static String intro = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static String question = "\"재귀함수가 뭔가요?\"";
	static String repeat1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
	static String repeat2 =	"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
	static String repeat3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static String end = "라고 답변하였지.";
	static int hope = 0; // hope를 입력받고 재귀 함수에서는 +1씩 늘려가며 수행하다 hope와 같으면 리턴
	
	static void sol(int num) {
		// hyphen 생성
		StringBuilder underbar = new StringBuilder();
		for(int i = 0; i < num; i++) 
			underbar.append("____");
		sb.append(underbar).append(question).append("\n");
		// 1. 종료 조건 num = hope
		if(num == hope) {
			sb.append(underbar).append(answer).append("\n");
		} else {
			sb.append(underbar).append(repeat1);
			sb.append(underbar).append(repeat2);
			sb.append(underbar).append(repeat3).append("\n");
			sol(++num);
		}
		sb.append(underbar).append(end).append("\n");
		return;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		hope = Integer.parseInt(st.nextToken());
		
		sb.append(intro).append("\n");
		sb.append(question).append("\n");
		sb.append(repeat1).append(repeat2).append(repeat3).append("\n");

		sol(1);
		sb.append(end);
		
		System.out.println(sb);
	}

}
