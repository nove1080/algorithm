package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2143 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int target; //덧셈의 결과

    static int n, m;
    static int[] arr1; //첫 번째 배열
    static int[] arr2; //두 번째 배열

    static int sumArrSize; //밑에 배열 크기
    static int[] arr2SumArr; //두 번째 배열의 부분합을 보관하는 배열

    static long answer = 0;
    public static void main(String[] args) throws Exception {
        init();
        // [풀이 전략]
        // arr1의 합배열
        // arr2의 합배열
        // 각각을 정렬한다.
        // 이진탐색을 통해 arr1 <->  arr2의 합이 t 가 되는 쌍을 찾는다.
            // arr1 하나당 하나의 arr2에 대응되는게 아니라 복수개가 대응 될 수 있으므로
            // lower,upper 경계를 통해 대응 되는 쌍의 개수를 찾아나간다.
        
        // [풀이의 시간복잡도]
        // 1. 합배열 생성 -> O(n^2) -> 2차원 배열 순회
        // 2. 합배열 생성 -> O(n^2) -> 2차원 배열 순회
        // 3. 정렬 -> O(N^2lgN^2) -> 합배열 크기에 비례한 시간 소요
        // 4. 이진탐색 수행 -> O(n^2) * O(lgN)
        // 약 3200만 연산 < 1억 -> 1초안에 풀이가 가능

        // 첫 번째 배열의 모든 부분합에 대하여 이진 탐색을 수행
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr1[j];
                answer += search(sum);
            }
        }

        System.out.println(answer);
    }

    public static void init() throws Exception {
        target = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(input[i]);
        }

        m = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(input[i]);
        }

        //합배열의 크기를 초기화
        sumArrSize = 0;
        for (int i = 1; i <= m; i++) {
            sumArrSize += i;
        }

        //합배열 초기화
        arr2SumArr = new int[sumArrSize];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += arr2[j];
                arr2SumArr[idx++] = sum;
            }
        }

        //이분탐색을 위한 합배열 정렬
        Arrays.sort(arr2SumArr);
    }

    /**
     * 파라미터와 더했을 때, target 이 되는 숫자의 개수를 반환
     * @param num 덧셈 피연산자 중 1개
     * @return arr2의 누적합 배열에서 target - num 의 개수
     */
    public static int search(int num) {
        return upperBound(target - num) - lowerBound(target - num);
    }

    public static int lowerBound(int target) {
        int st = 0;
        int en = sumArrSize;

        while (st < en) {
            int mid = (st + en) / 2;

            if(arr2SumArr[mid] < target) st = mid + 1;
            if(arr2SumArr[mid] >= target) en = mid;
        }

        return en;
    }

    public static int upperBound(int target) {
        int st = 0;
        int en = sumArrSize;

        while (st < en) {
            int mid = (st + en) / 2;

            if (arr2SumArr[mid] <= target) st = mid + 1;
            if (arr2SumArr[mid] > target) en = mid;
        }

        return en;
    }
}
