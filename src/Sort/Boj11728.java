//배열 합치기
package Sort;

import java.io.*;
import java.util.*;

public class Boj11728 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] arrA, arrB;
	static int[] arr;
	static int sizeA, sizeB;
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sizeA = Integer.parseInt(st.nextToken());
		sizeB = Integer.parseInt(st.nextToken());
		
		arrA = new int[sizeA];
		arrB = new int[sizeB];
		arr = new int[sizeA + sizeB];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < sizeA; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
//			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < sizeB; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
//			arr[i + sizeA] = Integer.parseInt(st.nextToken());
		}
	}
	
	//라이브러리 사용
	static void method1() throws Exception {
		init();
		Arrays.sort(arr);
		
		//printArr
		for(int k : arr) {
			sb.append(k + " ");
		}
		System.out.println(sb);
	}
	
	static void method2() throws Exception {
		init();
		
		int size = sizeA + sizeB;
		int idxA = 0, idxB = 0;
		for(int i = 0; i < size; i++) {
			if(idxA == sizeA) {
				arr[i] = arrB[idxB++];
			} 
			else if (idxB == sizeB) {
				arr[i] = arrA[idxA++];
			}
			else if(arrA[idxA] > arrB[idxB]) {
				arr[i] = arrB[idxB++];
			} 
			else {
				arr[i] = arrA[idxA++];
			}
		}
		
		//printArr
		for(int k : arr) {
			sb.append(k + " ");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		method2();
	}

}
