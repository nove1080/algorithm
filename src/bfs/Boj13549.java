package bfs;// ���ٲ��� 3 [G5]

// sol
//  1. "������ ã�� ���� ���� �ð�" -> �ִܰ��? -> 1���� BFS
//  2. BFS�� �ϵ�, �����̵��̶�� ������ �ȴ� �Ͱ� ������ �̵� ����̹Ƿ�
//  3. �����̵� -> �ȱ� -> �����̵� -> �ȱ� �� bfs�� ����
//  4. �����̵��� �ȱ⺸�� ���� �Ͼ�Ƿ�(0�� < 1��)

// �ð����⵵
// O(n) (n = size)

// �ٽ�
//  1. Queue 2���� ���� �����̵��ϰ� ���� �ɾ���� Q�� �߰�
//                          �Ȱ��� �����̵��� Q�� �߰�

// "0�� ��"�� �����̵�(2*X��)
import java.io.*;
import java.util.*;

public class Boj13549 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int size = 100001; // 200010 ���
	static int[] board = new int[size];
	static int[] time = new int[size];
	static int[] moveY = {1,-1};
	static int start, end;
	static Queue<Integer> teleportQ = new LinkedList();
	static Queue<Integer> walkQ = new LinkedList();
	static int curtime = 0;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		init();
		teleportQ.add(start);
		time[start] = 0;
		
		while(true) {
			if(teleport() || walk()) {
				System.out.println(curtime);
				return;
			}
		}
	}
	
	static void init() {
		for(int i = 0; i < size; i++) {
			time[i] = Integer.MAX_VALUE;
		}
	}
	
	static boolean teleport() {
		while(!teleportQ.isEmpty()) {
			int cur = teleportQ.poll();
			if(cur == end) return true;
			walkQ.add(cur);
			
			int ny = cur * 2;
				
			if(ny >= size) continue;
			if(time[ny] <= time[cur]) continue;
			
			teleportQ.add(ny);
			time[ny] = curtime;
		}
		curtime++;
		return false;
	}
	
	static boolean walk() {
		while(!walkQ.isEmpty()) {
			int cur = walkQ.poll();
			if(cur == end) return true;
			for(int i = 0; i < 2; i++) {
				int ny = cur + moveY[i];
				
				if(ny < 0 || ny >= size) continue;
				if(time[ny] <= time[cur]) continue;
				
				teleportQ.add(ny); // �ɾ ������ ���� tpQ�� ����
				time[ny] = curtime;
			}
		}
		return false;
	}
}
