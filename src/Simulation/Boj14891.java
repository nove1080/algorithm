//톱니바퀴 
//시간 : (00:45 ~ 02:00) 

/**
 * 톱니바퀴의 동작
 * 1. 회전 시, 서로 맞닿은 톱니바퀴의 극이 다르면 반대방향으로 회전한다.
 * 2. 회전 시, 서로 맞닿은 톱니바퀴의 극이 같으면 회전하지 않는다.
 * 
 * 시간복잡도
 *  - 32 * 100
 */
package Simulation;

import java.io.*;
import java.util.*;

public class Boj14891 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static Gear[] gears = new Gear[4];
	static int[] dirArr = new int[4]; //각 기어가 회전할 방향
	
	static void init() throws Exception {
		for(int i = 0; i < 4; i++) {
			char[] input = br.readLine().toCharArray();
			int[] gear = new int[8];
			for(int j = 0; j < 8; j++) {
				gear[j] = input[j] - '0';
			}
			gears[i] = new Gear(gear);
		}
	}
	
	static void rotate(int gearIdx, int dir) {
		dirArr[gearIdx] = dir;
		
		//기어 회전 방향 계산
		calculateDir(gearIdx, gearIdx - 1);
		calculateDir(gearIdx, gearIdx + 1);
		
		//실제 기어 회전
		for(int i = 0; i < 4; i++) {
			gears[i].rotate(dirArr[i]);
			dirArr[i] = 0; //초기화
		}
	}
	
	static void calculateDir(int from, int to) {
		if(to < 0 || to > 3) return;
		
		//앞의 기어를 돌리는 경우
		if(from < to) {
			if(gears[from].arr[2] != gears[to].arr[6]) {
				dirArr[to] = dirArr[from] * -1;
			}
			calculateDir(to, to + 1);
		}
		else {// 뒤의 기어를 돌리는 경우
			if(gears[from].arr[6] != gears[to].arr[2]) {
				dirArr[to] = dirArr[from] * -1;
			}
			calculateDir(to, to - 1);
		}
	}
	
	static int score() {
		int ans = 0;
		
		int point = 1;
		for(int i = 0; i < 4; i++, point*=2) {
			if(gears[i].arr[0] == 1) ans += point;
		}
		
		return ans;
	}

	public static void main(String[] args) throws Exception {
		init();
		
		int num = Integer.parseInt(br.readLine());
		for(int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int gearIdx = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			rotate(gearIdx, dir);
		}
		
		System.out.println(score());
	}

	static class Gear {
		int[] arr; //[0] : 12시 방향
		
		Gear(int[] arr) {
			this.arr = arr;
		}
		
		void rotate(int dir) {
			int tmp;
			if(dir == -1) { //반시계
				tmp = arr[0];
				for(int i = 0; i < 7; i++) {
					arr[i] = arr[i + 1];
				}
				arr[7] = tmp;
			}
			else if(dir == 1) { //시계
				tmp = arr[7];
				for(int i = 7; i > 0; i--) {
					arr[i] = arr[i - 1];
				}
				arr[0] = tmp;
			}
		}
	}
}
