//스티커 붙이기

/**
 * 핵심 idea
 *  - 2차원 배열 회전시키는 방법(Sticker.rotate() 참고)
 *  - 시간복잡도 계산하기
 */
package Simulation;

import java.io.*;
import java.util.*;

public class Boj18808 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board; //노트북
	static int maxR, maxC; //노트북의 가로,세로
	static Sticker[] stickerList;
	static int totalSticker; //스티커의 총 개수
	
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		maxR = Integer.parseInt(st.nextToken());
		maxC = Integer.parseInt(st.nextToken());
		totalSticker = Integer.parseInt(st.nextToken());
		
		//노트북 초기화
		board = new int[maxR][maxC];
		for(int i = 0; i < maxR; i++) {
			for(int j = 0; j < maxC; j++) {
				board[i][j] = 0;
			}
		}
		
		//스티커 입력받기
		stickerList = new Sticker[totalSticker];
		for(int i = 0; i < totalSticker; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] board = new int[r][c];
			
			for(int x = 0; x < r; x++) {
				st = new StringTokenizer(br.readLine());
				for(int y = 0; y < c; y++) {
					board[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			stickerList[i] = new Sticker(r, c, board);
		}
		
	}

	//param: idx = stickerList의 index
	static void tryAttach(int idx) {
		Sticker sticker = stickerList[idx];
		for(int k = 0; k < 4; k++) {
			if(k != 0) {
				sticker.rotate(); //3번째 for문 밑으로 넣으면 if문으로 구성안해도 된다.
			}
			for(int i = 0; i < maxR; i++) {
				for(int j = 0; j < maxC; j++) {
					if(isAttach(board, sticker, i, j)) {
						return;
					}
				}
			}
		}
	}
	
	// sticker의 크기만큼만 1번 비교하고 리턴
	static boolean isAttach(int[][] board, Sticker sticker, int r, int c) {
		//(r,c)에 sticker를 notebook에 붙일 수 있는지
		if(maxR - r < sticker.maxR || maxC - c < sticker.maxC) return false;
		//maxR = 5
		//maxC = 2
		for(int i = 0; i < sticker.maxR; i++) {
			for(int j = 0; j < sticker.maxC; j++) {
				if(sticker.board[i][j] == 1 && board[r+i][c+j] == 1) return false;
			}
		}
		
		//여기까지 오면 스티커를 붙일 수 있다
		for(int i = 0; i < sticker.maxR; i++) {
			for(int j = 0; j < sticker.maxC; j++) {
				if(sticker.board[i][j] == 1) {
					board[r+i][c+j] = 1;
				}
			}
		}
		
		return true;
	}
	
	static int getAnswer() {
		int cnt = 0;
		for(int i = 0; i < maxR; i++) {
			for(int j = 0; j < maxC; j++) {
				if(board[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}
	
	
	public static void main(String[] args) throws Exception {
		init();
		for(int i = 0; i < totalSticker; i++) {
			tryAttach(i);
		}
		System.out.println(getAnswer());
	}

	static class Sticker {
		int maxR, maxC;
		int[][] board;

		Sticker(int maxR, int maxC, int[][] board) {
			this.board = board;
			this.maxR = maxR;
			this.maxC = maxC;
		}

		//종이로 직접 그려서 회전 시 만들어지는 배열의 구조를 파악하여 코드화한다.
		void rotate() {
			int newR = maxC;
			int newC = maxR;
			int[][] newBoard = new int[newR][newC];

			for(int i = 0; i < newR; i++) {
				for(int j = 0; j < newC; j++) {
					newBoard[i][j] = board[maxR - j - 1][i];
				}
			}
			board = newBoard;
			maxR = newR;
			maxC = newC;
		}

	}

}