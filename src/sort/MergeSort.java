//Merge Sort


//라이브러리를 쓸 수 없다면 MergeSort를 구현하여 사용하도록 하자
//시간복잡도 : O(NlgN)
package sort;

public class MergeSort {
	
	static int n = 10;
	static int arr[] = new int[] {15, 25, 22, 357, 16, 23, -53, 12, 46, 3};
	static int tmp[] = new int[10];
	
	static void mergeSort(int st, int en) {
		if(en - st == 1) return;
		int mid = (st + en) / 2; 
		mergeSort(st, mid); 
		mergeSort(mid, en); 
		merge(st, en);
	}
	
	// st부터 en까지 정렬하는 함수
	static void merge(int st, int en) {
		int mid = (st + en) / 2;
		int lidx = st;
		int ridx = mid;
		
		for(int i = st; i < en; i++) {
			if(ridx == en) tmp[i] = arr[lidx++];
			else if(lidx == mid) tmp[i] = arr[ridx++];
			else if(arr[lidx] <= arr[ridx]) tmp[i] = arr[lidx++];
			else tmp[i] = arr[ridx++];
		}
		
		for(int i = st; i < en; i++)
			arr[i] = tmp[i];
	}
	
	public static void main(String[] args) {
		mergeSort(0, n);
		for(int k : arr) 
			System.out.print(k + " ");
	}

}
