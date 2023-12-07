//감시

package Simulation;

import java.io.*;
import java.util.*;

public class Boj15683 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] board;
	static int[][] checkBoard; //감시가 되는지 체크하는 보드
	static int maxR, maxC; //board size
	static int ans;
	
	static CCTV[] cctvList;
	static int cctvListSize;
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		maxR = Integer.parseInt(st.nextToken());
		maxC = Integer.parseInt(st.nextToken());
		ans = maxR*maxC;
		
		board = new int[maxR][maxC];
		checkBoard = new int[maxR][maxC];
		
		//board 생성 & CCTV 생성
		List<CCTV> tmpCCTVList = new ArrayList<>();
		for(int i = 0; i < maxR; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < maxC; j++) {
				int type = Integer.parseInt(st.nextToken());
				board[i][j] = type;
				checkBoard[i][j] = 0;
				if(type > 0 && type < 6) { // CCTV라면
					tmpCCTVList.add(new CCTV(i, j, type));
					checkBoard[i][j] = -1; // cctv는 스캔완료 판정
				} else if(type == 6) {
					checkBoard[i][j] = -2; // 벽은 스캔완료 판정
					ans--;
				}
			}
		}
		
		//CCTV 리스트 초기화
		cctvListSize = tmpCCTVList.size();
		cctvList = new CCTV[cctvListSize];
		Iterator it = tmpCCTVList.iterator();
		int idx = 0;
		while(it.hasNext()) {
			cctvList[idx++] = (CCTV)it.next();
		}
	}
	
	//cctvList를 순회하며 백트래킹 실시
	static void backtracking(int idx) {
		if(idx == cctvListSize) {
			save();
			return;
		}
		
		//cctv 유형에 따른 스캔횟수 줄이기
		switch(cctvList[idx].type) {
		case 1:
		case 3:
		case 4:
			cctvList[idx].rotate();
			cctvList[idx].scan();
			backtracking(idx + 1);
			cctvList[idx].rollback();
			
			cctvList[idx].rotate();
			cctvList[idx].scan();
			backtracking(idx + 1);
			cctvList[idx].rollback();
		case 2:
			cctvList[idx].rotate();
			cctvList[idx].scan();
			backtracking(idx + 1);
			cctvList[idx].rollback();
		case 5:
			cctvList[idx].rotate();
			cctvList[idx].scan();
			backtracking(idx + 1);
			cctvList[idx].rollback();
		}
		
	}
	
	//현재 스캔 불가구역을 기록
	static void save() {
		int unCheck = 0;
		for(int i = 0; i < maxR; i++) {
			for(int j = 0; j < maxC; j++) {
				if(checkBoard[i][j] == 0) unCheck++;
			}
		}
		
		if(ans > unCheck) {
			System.out.println("========== renew ==========");
			for(int i = 0; i < maxR; i++) {
				for(int j = 0; j < maxC; j++) {
					System.out.print(checkBoard[i][j] + " ");
				}
				System.out.println();
			}
			ans = unCheck;
			System.out.println("ans: " + ans);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		init();
		if(cctvListSize > 0) {
			backtracking(0);
		}
		System.out.println(ans);
	}

}

class CCTV {
	int type; //1,2,3,4,5
	int r, c; //cctv가 설치된 좌표
	boolean[] direction = new boolean[4]; //idx: 방향, value: 감시 가능한가
	
	CCTV(int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
		
		initDirection(type);
	}
	
	//CCTV유형에 맞는 감시방향 설정
	void initDirection(int type) {
		for(int i = 0; i < 4; i++) {
			direction[i] = false;
		}
		
		switch(type) {
		case 1:
			direction[0] = true;
			break;
		case 2:
			direction[0] = true;
			direction[2] = true;
			break;
		case 3:
			direction[0] = true;
			direction[1] = true;
			break;
		case 4:
			direction[0] = true;
			direction[1] = true;
			direction[2] = true;
			break;
		case 5:
			direction[0] = true;
			direction[1] = true;
			direction[2] = true;
			direction[3] = true;
		}
	}
	
	//스캔 가능 구역을 mark로 표시
	void scan() {
		//board에 6을 만날때까지 스캔 실시
		//dir[0] -> c는 고정, r쪽으로 - 방향 스캔
		//dir[1] -> r은 고정, c쪽으로 + 방향 스캔
		//dir[2] -> c는 고정, r쪽으로 + 방향 스캔
		//dir[3] -> r은 고정, c쪽으로 - 방향 스캔
		
		if(direction[0]) {
			for(int i = r; i >= 0; i--) {
				if(Boj15683.checkBoard[i][c] == -2) break;
				if(Boj15683.checkBoard[i][c] == -1) continue;
				Boj15683.checkBoard[i][c]++;
			}
		}
		
		if(direction[1]) {
			for(int i = c; i < Boj15683.maxC; i++) {
				if(Boj15683.checkBoard[r][i] == -2) break;
				if(Boj15683.checkBoard[r][i] == -1) continue;
				Boj15683.checkBoard[r][i]++;
			}
		}
		
		if(direction[2]) {
			for(int i = r; i < Boj15683.maxR; i++) {
				if(Boj15683.checkBoard[i][c] == -2) break;
				if(Boj15683.checkBoard[i][c] == -1) continue;
				Boj15683.checkBoard[i][c]++;
			}
		}
		
		if(direction[3]) {
			for(int i = c; i >= 0; i--) {
				if(Boj15683.checkBoard[r][i] == -2) break;
				if(Boj15683.checkBoard[r][i] == -1) continue;
				Boj15683.checkBoard[r][i]++;
			}
		}
	}
	
	//scan을 false로 호출
	void rollback() {
		if(direction[0]) {
			for(int i = r; i >= 0; i--) {
				if(Boj15683.checkBoard[i][c] == -2) break;
				if(Boj15683.checkBoard[i][c] == -1) continue;
				if(Boj15683.checkBoard[i][c] - 1 < 0) Boj15683.checkBoard[i][c] = 0;
				else Boj15683.checkBoard[i][c]--;
			}
		}
		
		if(direction[1]) {
			for(int i = c; i < Boj15683.maxC; i++) {
				if(Boj15683.checkBoard[r][i] == -2) break;
				if(Boj15683.checkBoard[r][i] == -1) continue;
				if(Boj15683.checkBoard[r][i] - 1 < 0) Boj15683.checkBoard[r][i] = 0;
				else Boj15683.checkBoard[r][i]--;
			}
		}
		
		if(direction[2]) {
			for(int i = r; i < Boj15683.maxR; i++) {
				if(Boj15683.checkBoard[i][c] == -2) break;
				if(Boj15683.checkBoard[i][c] == -1) continue;
				if(Boj15683.checkBoard[i][c] - 1 < 0) Boj15683.checkBoard[i][c] = 0;
				else Boj15683.checkBoard[i][c]--;
			}
		}
		
		if(direction[3]) {
			for(int i = c; i >= 0; i--) {
				if(Boj15683.checkBoard[r][i] == -2) break;
				if(Boj15683.checkBoard[r][i] == -1) continue;
				if(Boj15683.checkBoard[r][i] - 1 < 0) Boj15683.checkBoard[r][i] = 0;
				else Boj15683.checkBoard[r][i]--;
			}
		}
	}
	
	//CCTV를 90도 회전
	void rotate() {
		boolean[] newDirection = new boolean[4];
		for(int i = 0; i < 4; i++) {
			newDirection[i] = false;
		}
		for(int i = 0; i < 4; i++) {
			if(direction[i]) {
				int rotate = (i+1)%4;
				newDirection[rotate] = true;
			}
		}
		direction = newDirection;
	}

}
