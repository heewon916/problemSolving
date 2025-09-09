import java.io.*;
import java.util.*;

public class Solution_d3_3282_01Knapsack_서울_8반_김희원 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); 
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] pkg = new int[N+1][2]; // 1번 물건: (부피, 가치)
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int w = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                pkg[i][0] = w; 
                pkg[i][1] = cost; 
            }

            int[] dp = new int[K+1];
            Arrays.fill(dp, 0);

            // 역순으로 물건 i에 대해서 
            // 나의 무게 w_i, 가방의 용량 w에 대해서 
            // w == 0 이거나, i == 0이면 0 
            // w_i > w이면 dp[w] 유지 
            // w_i <= w 이면 넣을 수 있으니까 dp[w] = max(dp[w], dp[w-w_i] + v_i)
            for(int i=0; i<N; i++){
                int w_i = pkg[i][0]; // 부피 
                int v_i = pkg[i][1]; // 가치 
                for(int w=K; w>=0; w--){
                    if(w_i <= w){
                        dp[w] = Math.max(dp[w], dp[w-w_i] + v_i);
                    }
                }
                // System.out.println("i=" + (i+1) + " " + Arrays.toString(dp));
            }
            sb.append("#").append(tc).append(' ').append(dp[K]).append('\n');
            
        }
        System.out.println(sb.toString());
    }
}
