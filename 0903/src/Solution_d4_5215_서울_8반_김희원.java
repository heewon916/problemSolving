import java.io.*;
import java.util.*;

public class Solution_d4_5215_서울_8반_김희원 {
    static int N, L; 
    static int[] scores; 
    static int[] calories; 

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            scores = new int[N+1]; 
            calories = new int[N+1]; 

            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            /*
             * 풀이1. 2차원 배열을 이용한 DP top-down 풀이 
             * dp[i][j]: i번째 재료까지 고려했을 때, 칼로리 j 이하로 얻을 수 있는 최대 점수 
             * 점화식: 
             * dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - calories[i]] + scores[i])
             * dp[i-1][j]: i번째 재료를 선택하지 않는 경우
             * dp[i-1][j - calories[i]] + scores[i]: i번째 재료를 선택하는 경우
             */
            
            // int[][] dp = new int[N+1][L+1];
            // 초기화: 0번째 재료(가상의 재료)까지 고려했을 때, 모든 칼로리에서 점수는 0
            // for(int i=0; i<=L; i++) dp[0][i] = 0; 
            // for(int i=1; i<=N; i++){
            //     for(int j=0; j<=L; j++){
                        // 현재 재료의 칼로리가 제한 칼로리보다 크면 선택 불가
            //         if(calories[i] > j) dp[i][j] = dp[i-1][j]; 
            //         else dp[i][j] = Math.max(dp[i-1][j], (dp[i-1][j-calories[i]]) + scores[i]);
            //     }
            // }

            /*
             * 풀이2. 1차원 배열을 이용한 0-1 냅색 
             * dp[j]: 칼로리 j 이하로 얻을 수 있는 최대 점수
             */
            int[] dp = new int[L+1];
            for(int i=calories[0]; i<=L; i++) dp[i] = scores[0]; 
            for(int i=1; i<=N; i++){
                // 칼로리 제한 L부터 현재 재료의 칼로리까지 역순으로 순회
                // 역순으로 순회하는 이유는 **한 재료를 두 번 선택하는 것을 방지**하기 위함
                // (만약 정방향으로 순회하면, dp[j - calories[i]]가 이미 현재 재료를 포함한 값이 되어버림)
                for(int j=L; j>=calories[i]; j--){
                    // 현재 dp[j] 값 (i번째 재료를 선택하지 않는 경우)
                    // vs
                    // dp[j - calories[i]] + scores[i] (i번째 재료를 선택하는 경우)
                    // 두 값 중 더 큰 값으로 dp[j]를 갱신
                    dp[j] = Math.max(dp[j], dp[j-calories[i]] + scores[i]);
                }
            }
            // sb.append("#").append(t).append(' ').append(dp[N][L]).append('\n');
            sb.append("#").append(t).append(' ').append(dp[L]).append('\n');
        }
        System.out.println(sb.toString());
    }
}
