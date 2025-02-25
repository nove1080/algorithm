package bfs;

import java.io.*;
import java.util.*;

public class Boj2606 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int computer;
	static int network;

	static ArrayList<Integer>[] adjList;
	static boolean[] vis;

	public static void main(String[] args) throws Exception{
		init();

		System.out.println(bfs(0));
	}

	public static void init() throws Exception {
		computer = Integer.parseInt(br.readLine());
		network = Integer.parseInt(br.readLine());

		vis = new boolean[computer];

		adjList = new ArrayList[computer];
		for (int i = 0; i < computer; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < network; i++) {
			String[] input = br.readLine().split(" ");
			int num1 = Integer.parseInt(input[0]) - 1;
			int num2 = Integer.parseInt(input[1]) - 1;

			adjList[num1].add(num2);
			adjList[num2].add(num1);
		}
	}

	public static int bfs(int start) {
		int count = 0; //감염된 컴퓨터 수
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		vis[start] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			for(Integer next : adjList[cur]) {
				if(vis[next]) continue;

				q.add(next);
				vis[next] = true;
				count++;
			}
		}

		return count;
	}
}
