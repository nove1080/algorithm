package bfs;// ��ŸƮ��ũ [S1]

// sol
//  1. �ִ� �Ÿ��� ���ϴ� ���� -> BFS
//  2. �� 1�������� BFS�� ���� + �����ǿ��� �̵� ����� ���� ����

import java.util.*;
import java.io.*;

public class Boj5014 {
	static int[] time;
	static int[] move = new int[2];
	static int height, now, goal;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		height = Integer.parseInt(st.nextToken());
		now = Integer.parseInt(st.nextToken()) - 1;
		goal = Integer.parseInt(st.nextToken()) - 1;
		move[0]= Integer.parseInt(st.nextToken());
		move[1] = Integer.parseInt(st.nextToken()) * -1;
		
		time = new int[height];
		
		Arrays.fill(time, Integer.MAX_VALUE);
		
		System.out.println(bfs());
	}
	
	static String bfs() {
		Queue<Integer> q = new LinkedList();
		q.add(now);
		time[now] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == goal) {
				return String.valueOf(time[cur]);
			}
			for(int i = 0; i < 2; i++) {
				int next = cur + move[i];
				
				if(next < 0 || next >= height) continue;
				if(time[next] <= time[cur] + 1) continue;
				
				q.add(next);
				time[next] = time[cur] + 1;
			}
		}
		
		return "use the stairs";
	}
}
