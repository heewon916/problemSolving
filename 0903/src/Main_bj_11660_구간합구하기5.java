import java.io.*; 
import java.util.*; 
public class Main_bj_11660_구간합구하기5 {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N+1][N+1]; 
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<N+1; j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N+1][N+1]; 
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + mat[i][j]; 
            }
            System.out.println(Arrays.toString(dp[i]));
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int i1 = Integer.parseInt(st.nextToken());
            int j1 = Integer.parseInt(st.nextToken());
            int i2 = Integer.parseInt(st.nextToken());
            int j2 = Integer.parseInt(st.nextToken());

            long sum = dp[i2][j2] - dp[i1-1][j2] - dp[i2][j1-1] + dp[i1-1][j1-1];
            sb.append(sum).append('\n'); 
        }
        System.out.println(sb.toString());
    }
}
/*
[풀이]
2,2 부터 3,4까지의 합 
2,2 3
2,3 4
2,4 5
3,2 4
3,3 5
3,4 6

N 10^3 
구하는 횟수 M 10^5 

---
1초 
연산은 10^3까지만 가능하다. 

누적합 
1 3 6 10 
2 5 9 14 
3 7 12 18 
4 9 15 22 

2,2부터 3,4까지 
i1, j1 ~ i2, j2 
2,2부터 2,4까지 
같은 행이면 i1 == i2 
    dp[j2] - dp[j1-1] = dp[4] - dp[1] = 14 - 2 = 12
다른 행이면 
    i1< i2라고 가정하자. 
    
 */