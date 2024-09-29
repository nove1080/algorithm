//치킨 배달

/**
 * 
 * [핵심]
 *  -> 조합 구현하기
 * 
 * 모든 경우의 수를 따지면 시간복잡도 : 100 * 6 * (13C6(= 1716) + 알파) 
 * (문제 낚시)
 *   치킨집을 최대 M개 고른다 -> 사실 바로 M개를 고르면 치킨거리 최소를 얻을 수 있다.
 *   
 */
package simulation;

import java.util.*;
import java.io.*;

public class Boj15686 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m;
	
	static Point[] house; //집 배열
	static Point[] chicken; //치킨집 배열
	static int[] distance; //치킨거리 저장
	static boolean[] isSelected; //선택된 치킨집 m개
	static int houseNum; //집의 숫자
	static int chickenNum; //치킨집의 숫자
	static int ans = 9999999; //치킨 거리의 최솟값
	
	static void init() throws Exception{
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[13];
		chicken = new Point[13];
		house = new Point[2*n];
		distance = new int[2*n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int mark = Integer.parseInt(st.nextToken());
				if(mark == 1) house[houseNum++] = new Point(i, j);
				else if(mark == 2) chicken[chickenNum++] = new Point(i, j);
			}
		}
	}
	
	static int generateDist() {
		int cnt = 0;
		for(int i = 0; i < houseNum; i++) {
			for(int j = 0; j < chickenNum; j++) {
				if(isSelected[j]) {
					int dist = Math.abs(house[i].x - chicken[j].x) + Math.abs(house[i].y - chicken[j].y);
					if(distance[i] > dist) distance[i] = dist;				
				}
			}
			cnt += distance[i];
		}
		
		return cnt;
	}
	
	static void clearDist() {
		for(int i = 0; i < houseNum; i++) {
			distance[i] = 9999;
		}
	}
	
	static void combination(int n, int r, int idx, int size) {
		if(size == r) {
			int result = generateDist();
			if(ans > result) ans = result;
			clearDist();
			return;
		}
		
		for(int i = idx + 1; i < n; i++) {
			if(n - i < r - size) return;
			isSelected[i] = true;
			combination(n, r, i, size + 1);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception{
		init();
		clearDist();
		combination(chickenNum, m, -1, 0);
		System.out.println(ans);
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
