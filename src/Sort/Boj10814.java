//나이순 정렬

package Sort;

import java.io.*;
import java.util.*;

public class Boj10814 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static Person[] arr;
	static Person[] tmp;
	
	static void init() throws Exception {
		n = Integer.parseInt(br.readLine());
		
		arr = new Person[n]; 
		tmp = new Person[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			arr[i] = new Person(age, name);
		}
	}
	
	static void mergeSort(int start, int end) {
		if(end - start == 1) return;
		
		int middle = (int) Math.ceil((start + end) / 2);
		mergeSort(start, middle);
		mergeSort(middle, end);
		merge(start, end);
	}
	
	static void merge(int start, int end) {
		int middle = (start + end) / 2;
		
		int lidx = start;
		int ridx = middle;
		
		for(int i = start; i < end; i++) {
			if(lidx == middle) tmp[i] = arr[ridx++];
			else if(ridx == end) tmp[i] = arr[lidx++];
			else if(arr[lidx].age <= arr[ridx].age) tmp[i] = arr[lidx++];
			else tmp[i] = arr[ridx++];
		}
		
		for(int i = start; i < end; i++) {
			arr[i] = tmp[i];
		}
	}
	
	static void printArr() {
		for(Person p : arr) {
			sb.append(p + "\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		mergeSort(0, n);
		printArr();
	}
	
	static class Person {
		int age;
		String name;
		
		Person(int age, String name) {
			this.age = age;
			this.name = name;
		}
		
		public String toString() {
			return age + " " + name;
		}

	}

}
