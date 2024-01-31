// ���ڿ� ����

import java.io.*;

public class Boj9935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static char[] cArr;
	static char[] boom;
	static int cursor;
	
	static void init() throws Exception {
		cArr = br.readLine().toCharArray();
		boom = br.readLine().toCharArray();
	}
	
	static void remove(int start, int end) {
		for(int i = start; i <= end; i++) {
			cArr[i] = ' ';
		}
	}
	
	static void save() {
		boolean isEmpty = true;
		for(char c : cArr) {
			if(c != ' ') {
				sb.append(c);
				isEmpty = false;
			}
		}
		
		if(isEmpty) sb.append("FRULA");
	}
	
	public static void main(String[] args) throws Exception {
		init();
		
		boolean isClear = true; // �� �̻� ������ �ʿ䰡 �ִ°�?
		for(int i = 0; i < cArr.length; i++) {
			if(boom[cursor] == cArr[i]) {
				cursor++;
				if(cursor == boom.length) {
					int start = i - cursor + 1;
					remove(start, i);
					isClear = false;
					cursor = 0;
				}
			} 
			else { // ��ġ�� �ȵ� ��� �ٽ� ù��° boom�� �� �غ����Ѵ�.
				cursor = 0;
				if(boom[cursor] == cArr[i]) {
					cursor++;
					if(cursor == boom.length) {
						int start = i - cursor + 1;
						remove(start, i);
						isClear = false;
						cursor = 0;
					}
				} 
			}
		}
		while(!isClear) {
			int emptyCount = 0;
			isClear = true;
			for(int i = 0; i < cArr.length; i++) {
				if(cArr[i] == ' ') {
					emptyCount++;
					continue;
				}
				if(boom[cursor] == cArr[i]) {
					cursor++;
					if(cursor == boom.length) {
						int start = i - cursor + 1 - emptyCount;
						remove(start, i);
						emptyCount = 0;
						isClear = false;
						cursor = 0;
					}
				} 
				else { // ��ġ�� �ȵ� ��� �ٽ� ù��° boom�� �� �غ����Ѵ�.
					cursor = 0;
					if(boom[cursor] == cArr[i]) {
						cursor++;
						if(cursor == boom.length) {
							int start = i - cursor + 1 - emptyCount;
							remove(start, i);
							emptyCount = 0;
							isClear = false;
							cursor = 0;
						}
					} 
					// boom ù��°�͵� ��Ī�� �ȵ� ��¥ �ٸ� ������ ��� ����� ī���� �ʱ�ȭ
					if(cursor == 0) emptyCount = 0;
				}
			}
		}
		
		save();
		System.out.println(sb);
	}

}
