import java.util.*;
import java.io.*; 

public class Solution_d3_5215_햄버거다이어트_NP_서울_8반_김희원 {

    static int N; // 재료의 수
    static int L; // 제한 칼로리
    static int[] scores; // 각 재료의 맛 점수 배열
    static int[] calories; // 각 재료의 칼로리 배열
    static int maxScore; // 최대 맛 점수를 저장할 변수

    public static void main(String[] args) throws Exception {
    	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	 StringBuilder sb = new StringBuilder();
    	 StringTokenizer st = null;
         int T = Integer.parseInt(br.readLine());

         for (int test_case = 1; test_case <= T; test_case++) {
             st = new StringTokenizer(br.readLine());
             N = Integer.parseInt(st.nextToken());
             L = Integer.parseInt(st.nextToken());

             scores = new int[N];
             calories = new int[N];

             for (int i = 0; i < N; i++) {
                 st = new StringTokenizer(br.readLine());
                 scores[i] = Integer.parseInt(st.nextToken());
                 calories[i] = Integer.parseInt(st.nextToken());
             }

             maxScore = 0;
             solve(); // Next Permutation을 이용한 해결 함수 호출

             sb.append("#").append(test_case).append(" ").append(maxScore).append("\n");
         }
         System.out.print(sb);
     }

     private static void solve() {
         // 조합을 생성하기 위한 배열 (0: 미선택, 1: 선택)
         int[] p = new int[N];

         // 1개부터 N개까지의 재료를 선택하는 모든 경우의 수를 확인
         for (int k = 1; k <= N; k++) {
             // 1. 배열의 뒤쪽부터 k개의 1을 채워 초기 조합 생성 (예: N=5, k=3 -> [0, 0, 1, 1, 1])
             int count = 0;
             while(++count <= k) {
                 p[N - count] = 1;
             }

             // 2. 현재 조합(p)에 대해 점수/칼로리 계산 후 다음 조합 생성
             do {
                 int currentScore = 0;
                 int currentCalorie = 0;

                 // p 배열을 보고 선택된 재료(1)들의 점수와 칼로리를 합산
                 for (int i = 0; i < N; i++) {
                     if (p[i] == 1) {
                         currentScore += scores[i];
                         currentCalorie += calories[i];
                     }
                 }

                 // 칼로리 제한을 만족하면 최대 점수 갱신
                 if (currentCalorie <= L) {
                     maxScore = Math.max(maxScore, currentScore);
                 }

             } while (nextPermutation(p)); // 다음 조합(순열) 생성
             
             // 다음 k를 위해 배열을 0으로 초기화
             Arrays.fill(p, 0);
         }
     }

     /**
      * Next Permutation 알고리즘
      * @param arr 순열을 생성할 배열
      * @return 다음 순열이 존재하면 true, 마지막 순열이면 false
      */
     private static boolean nextPermutation(int[] arr) {
         // 1. 교환 위치(i-1) 찾기 (뒤에서부터 오름차순이 깨지는 지점)
         int i = N - 1;
         while (i > 0 && arr[i-1] >= arr[i]) {
             i--;
         }
         // i가 0이면 마지막 순열이므로 false 반환
         if (i == 0) return false;

         // 2. 교환할 대상(j) 찾기 (i-1 위치의 값보다 큰 첫번째 값)
         int j = N - 1;
         while (arr[i-1] >= arr[j]) {
             j--;
         }

         // 3. i-1 위치의 값과 j 위치의 값을 교환(swap)
         swap(arr, i - 1, j);

         // 4. i 위치부터 끝까지의 순서를 뒤집기
         int k = N - 1;
         while (i < k) {
             swap(arr, i++, k--);
         }
         
         return true;
     }

     private static void swap(int[] arr, int i, int j) {
         int temp = arr[i];
         arr[i] = arr[j];
         arr[j] = temp;
     }

}

/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
*/
