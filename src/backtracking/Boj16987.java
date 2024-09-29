//계란으로 계란치기
package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static Egg[] eggs;
	static int result;

	public static void main(String[] args) throws Exception {
		init();
		backtracking(0);
		System.out.println(result);
	}

	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		eggs = new Egg[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int hp = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(hp, power);
		}
	}

	static void backtracking(int attackerIdx) {
		if (attackerIdx == eggs.length) {
			//깨진 계란 수를 저장
			int count = 0;
			for (Egg egg : eggs) {
				if(egg.hp <= 0) count++;
			}
			result = Math.max(count, result);
			return;
		}

		int temp = 0;
		for (int defenderIdx = 0; defenderIdx < n; defenderIdx++) {
			Egg e1 = eggs[attackerIdx];
			Egg e2 = eggs[defenderIdx];

			if (e1.hp <= 0) {
				backtracking(attackerIdx + 1);
			}
			else if (e2.hp <= 0 || e1 == e2) {
				temp++;
				if(temp == n) {	//나 빼고 다른 모든 계란이 부서짐
					backtracking(attackerIdx + 1);
				}
			}
			//계란치기
			else {
				e1.hit(e2);
				backtracking(attackerIdx + 1);
				e1.recover(e2);
			}
		}
	}

	static class Egg {
		int hp;
		int power;
		public Egg(int hp, int power) {
			this.hp = hp;
			this.power = power;
		}

		public void hit(Egg target) {
			target.hp -= this.power;
			this.hp -= target.power;
		}

		public void recover(Egg target) {
			target.hp += this.power;
			this.hp += target.power;
		}
	}
}
