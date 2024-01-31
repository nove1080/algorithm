// ���� ����� ���� [S2]

// sol
// 1. BFS

import java.util.*;
import java.io.*;

public class Boj11724 {
	static int[] check;
	static int[] vis;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		vis = new int[m];
		check = new int[n]; // ȥ�� �ִ� �� ã��
		
		Point[] pList = new Point[m];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			check[x] = 1; check[y] = 1;
			vis[i] = 0;
			pList[i] = new Point(x, y);
		}
		Queue<Point> q = new LinkedList();
		
		for(int i = 0; i < m; i++) {
			// ���� ����� ������
			if(vis[i] == 0) {
				ans++;
				q.add(new Point(pList[i].x, pList[i].y));
				vis[i] = 1;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					
					for(int k = i; k < m; k++) {
						if(vis[k] == 1) continue;
						if(pList[k].x == cur.x) {
							q.add(pList[k]); 
						} 
						else if(pList[k].x == cur.y) {
							q.add(pList[k]); 
						}
						else if(pList[k].y == cur.x) {
							q.add(pList[k]); 
						}
						else if(pList[k].y == cur.y) {
							q.add(pList[k]); 
						} else continue;
						vis[k] = 1;
					}
				}
			}
		}
		
		for(int a : check) {
			if(a == 0) ans++;
		}
		System.out.println(ans);
	} // main

}

//class Point{
//	int x, y;
//	Point(int x, int y){
//		this.x = x;
//		this.y = y;
//	}
//}