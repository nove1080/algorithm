// �� ������Ʈ[G3]

// sol
//  1. ���� �� �� �ִ� ����
//   1-1. Cycle�� �����Ǹ� �� Cycle�� ���̴�. (�ٽ�)

//  2. Cycle�� �Ǵ��ϴ� ��
//   2-1. func : Cycle(int start) 
//   2-2. ���ʷ� ȣ���� idx�� ���ƿ��� Cycle
//    2-2-1. vis[idx] == 2

//  3. ���� 100% ���� ���ϴ� ����
//   3-1. ���� �ȵ� ����� ����� ���
//   3-2. �̹� ���� �ٸ� ����� ����� ���
import java.util.*;
import java.io.*;

public class Boj9466 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder(); // save ans

	static int[] hope;
	static int[] vis; // -1 : ���� �� ���, -2 : ���� �ȵ� ���
	static int size;
	
	public static void main(String[] args) throws Exception{
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- != 0) {
			size = Integer.parseInt(br.readLine());
			hope = new int[size];
			vis = new int[size];
			
			init();
			
			for(int idx = 0; idx < size; idx++) {
				if(vis[idx] == 0)
					cycleCheck(idx);
			}
//			printVis();
			sb.append(checkNoTeam()).append("\n");
		}
		System.out.println(sb);
	}
	
	// init : hope and vis 
	static void init() throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < size; i++) {
			hope[i] = Integer.parseInt(st.nextToken()) - 1;
			vis[i] = 0;
		}
	}
	
	static void cycleCheck(int studentNum) {
		// stack�� ����� �л��� ��ȣ�� �����ϸ� ����
		// ������ �� vis���� Ȯ���ϸ鼭 2���߸� �׶��� �л� ��ȣ�� ���ö�����
		// pop�ϰ� pop�� �������� �л���ȣ���� ���̵ȴ�.
		Stack<Integer> stk = new Stack();
		stk.push(studentNum);
		vis[studentNum]++;
		
		while(true) {
			
			int myHopeStu = hope[studentNum];
			
			// ��� : cycle ���� ����(�� X)
			if(vis[myHopeStu] == -1 || vis[myHopeStu] == -2) {
				while(!stk.isEmpty()) {
					vis[stk.pop()] = -2;
				}
				return;
			} 
			// ��� : cycle ���� or subcycle ���� (�� O)
			else if(vis[myHopeStu] == 1) {
				while(!stk.isEmpty()) {
					int tmp = stk.pop();
					vis[tmp] = -1;
					
					// cycle�� ���� ���� ������ �κ� (�� X)
					if(tmp == myHopeStu) {
						while(!stk.isEmpty()) {
							vis[stk.pop()] = -2;
						}
						return;
					}	
				}
			}
			
			stk.push(myHopeStu);
			vis[myHopeStu]++;
			studentNum = myHopeStu;
		}
	}
	
	static int checkNoTeam() {
		int cnt = 0;
		for(int i = 0; i < size; i++) {
			if(vis[i] == -2) cnt++;
		}
		return cnt;
	}
	
	static void printVis() {
		System.out.println("printVis()==========");
		for(int i = 0; i < size; i++) {
			System.out.print(vis[i] + " ");
		}
		System.out.println();
	}
}












