// 계란으로 계란치기

package Back_Tracking;

import java.util.*;
import java.io.*;

public class Boj16987 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int size, ans;
	static Egg[] eggArr;
	static Egg[] copyArr;
	static boolean[] isBroken;
	
	static void init() throws Exception {
		size = Integer.parseInt(br.readLine());
		eggArr = new Egg[size];
		copyArr = new Egg[size];
		isBroken = new boolean[size];
		
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int durability = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			eggArr[i] = new Egg(durability, weight);
			copyArr[i] = new Egg(durability, weight);
		}
	}
	
	//idx: 공격 계란 인덱스
	static void backtracking(int idx) {
		//base condition
		if(idx == size) {
			save();
			return;
		}
		//깨진 계란을 고르면 다음 계란을 공격 계란
		if(isBroken[idx]) {
			backtracking(idx + 1);
			return;
		}
		
		//수비 계란 선정
		for(int i = 0; i < size; i++) {
			if(i == idx) continue;
			if(!isBroken[i]) {
				kkwang(idx, i);
				backtracking(idx + 1);
				unkkwang(idx, i); // 언제 unkkwang할 지 고르기
			}
		}
		
		if(idx == size - 1) save();
	}
	
	//깨진 계란 수 저장
	static void save() {
//		System.out.println("======== save() ========");
		int cnt = 0;
		for(boolean b : isBroken) {
//			System.out.print(b + " -> ");
			if(b) cnt++;
		}
//		System.out.println();
		if(cnt > ans) 
			ans = cnt;
	}
	
	//계란 부딪치기
	static void kkwang(int idx1, int idx2) {
//		System.out.println("==== kkwang() ====");
//		System.out.println(idx1 + " vs " + idx2);
		int result = 0;
		Egg egg1 = eggArr[idx1];
		Egg egg2 = eggArr[idx2];
		
		// 계란의 상태를 저장
		egg1.durability = egg1.durability - egg2.weight;
		egg2.durability = egg2.durability - egg1.weight;
		
		if(egg1.durability <= 0) {
//			System.out.println(idx1 + " was broken");
			isBroken[idx1] = true;
		}
		if(egg2.durability <= 0) {
//			System.out.println(idx2 + " was broken");
			isBroken[idx2] = true;
		}
		
//		System.out.println();
		
		return;
	}
	
	//계란 상태 복원
	static void unkkwang(int idx1, int idx2) {
		Egg attack = eggArr[idx1];
		Egg defense = eggArr[idx2];
		
		// 계란의 상태를 저장
		attack.durability = attack.durability + defense.weight;
		defense.durability = defense.durability + attack.weight;
		
		isBroken[idx1] = false;
		isBroken[idx2] = false;
	}

	public static void main(String[] args) throws Exception {
		init();
		backtracking(0);
		System.out.println(ans);
	}

	static class Egg {
		int durability; //내구도
		int weight;		//무게

		Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;
		}

		public String toString() {
			return "(" + durability + ", " + weight + ")";
		}
	}

}
