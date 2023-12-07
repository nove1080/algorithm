//2048

/**
 * 시간복잡도 :20 * 20 * 20 * 20 * 4^5
 *
 * idea : backtracking
 * 
 * 
 */

package Simulation;

import java.io.*;
import java.util.*;

public class Boj12100 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] board;
	static int n; //board size
	
	static int ans;
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/*
	 * Param: num - 이동횟수
	 */
	static void backtracking(int num) {
		if(num == 5) {
			save();
			return;
		}
		int[][] tmpBoard = backup();
		for(int i = 0; i < 4; i++) {
			move(i); // board를 i 방향으로 스와이프
			backtracking(num + 1);
			board = tmpBoard;
		}
	}
	
	static void save() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] > ans) {
					printBoard();
					ans = board[i][j];
				}
			}
		}
	}
	
	/*
	 * Param: dir - 이동방향
	 */
	static void move(int dir) {
		switch(dir) {
		case 0: 
			up();
			break;
		case 1: 
			right();
			break;
		case 2: 
			down();
			break;
		case 3: 
			left();
			break;
				
		}
	}
	
	static void up() {
		boolean[] wasMerged = new boolean[n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[j][i] != 0) {
					int movePoint = j; //블록이 움직여질 자리
					boolean isMoved = false;
					for(int k = j - 1; k >= 0; k--) {
						if(board[k][i] == 0) {
							movePoint = k;
						}
						else if(board[k][i] == board[j][i] && !wasMerged[k]) {
							board[k][i] *= 2;
							board[j][i] = 0;
							wasMerged[k] = true;
							isMoved = true;
							break;
						}
						else {//0도 아니고, 합쳐지지도 않는다면 
							break;
						}
					}
					if(!isMoved && movePoint != j) {
						board[movePoint][i] = board[j][i];
						board[j][i] = 0;
					}
				}
			}
		}
	}
	static void right() {
		boolean[] wasMerged = new boolean[n];
		for(int i = 0; i < n; i++) {
			for(int j = n - 1; j >= 0; j--) {
				if(board[i][j] != 0) {
					int movePoint = j; //블록이 움직여질 자리
					boolean isMoved = false;
					for(int k = j + 1; k < n; k++) {
						if(board[i][k] == 0) {
							movePoint = k;
						}
						else if(board[i][k] == board[i][j] && !wasMerged[k]) {
							board[i][k] *= 2;
							board[i][j] = 0;
							wasMerged[k] = true;
							isMoved = true;
							break;
						}
						else {//0도 아니고, 합쳐지지도 않는다면 
							break;
						}
					}
					if(!isMoved && movePoint != j) {
						board[i][movePoint] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		}
	}
	static void down() {
		boolean[] wasMerged = new boolean[n];
		for(int i = 0; i < n; i++) {
			for(int j = n - 1; j >= 0; j--) {
				if(board[j][i] != 0) {
					int movePoint = j; //블록이 움직여질 자리
					boolean isMoved = false;
					for(int k = j + 1; k < n; k++) {
						if(board[k][i] == 0) {
							movePoint = k;
						}
						else if(board[k][i] == board[j][i] && !wasMerged[k]) {
							board[k][i] *= 2;
							board[j][i] = 0;
							wasMerged[k] = true;
							isMoved = true;
							break;
						}
						else {//0도 아니고, 합쳐지지도 않는다면 
							break;
						}
					}
					if(!isMoved && movePoint != j) {
						board[movePoint][i] = board[j][i];
						board[j][i] = 0;
					}
				}
			}
		}
	}
	
	static void left() {
		boolean[] wasMerged = new boolean[n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] != 0) {
					int movePoint = j; //블록이 움직여질 자리
					boolean isMoved = false;
					for(int k = j - 1; k >= 0; k--) {
						if(board[i][k] == 0) {
							movePoint = k;
						}
						else if(board[i][k] == board[i][j] && !wasMerged[k]) {
							board[i][k] *= 2;
							board[i][j] = 0;
							wasMerged[k] = true;
							isMoved = true;
							break;
						}
						else {//0도 아니고, 합쳐지지도 않는다면 
							break;
						}
					}
					if(!isMoved && movePoint != j) {
						board[i][movePoint] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		}
	}	
	
	static int[][] backup() {
		int[][] tmp = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tmp[i][j] = board[i][j];
			}
		}
		return tmp;
	}
	
	static void printBoard() {
		System.out.println("printBoard=============");
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		backtracking(0);
		
	}

}
