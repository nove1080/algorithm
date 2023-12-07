//수 정렬하기 2

/**
 * 정렬 -> O(nlgn)으로 해결하라
 */
package Sort;

import java.io.*;
import java.util.*;

public class Boj2751 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static int[] arr;
	static int[] tmp; //새로 계속 생성하는 것보다 전역에 하나 만들어 두는게 메모리 절약
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tmp = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	}
	
	static void mergeSort(int start, int end) {
		if(end - start == 1) return;
		
		int middle = (start + end) / 2;
		mergeSort(start, middle);
		mergeSort(middle, end);
		merge(start, end);
	}
	
	static void merge(int start, int end) {
		int middle = (start + end) / 2;
		int leftIdx = start;
		int rightIdx = middle;
		
		for(int i = start; i < end; i++) {
			if(leftIdx == middle) tmp[i] = arr[rightIdx++];
			else if(rightIdx == end) tmp[i] = arr[leftIdx++];
			else if(arr[leftIdx] < arr[rightIdx]) tmp[i] = arr[leftIdx++];
			else tmp[i] = arr[rightIdx++];
		}
		
		for(int i = start; i < end; i++) arr[i] = tmp[i];
	}
	
	static void printArr() {
		for(int k : arr) {
			sb.append(k + "\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception{
		init();
		mergeSort(0, n);
		printArr();
	}

}
