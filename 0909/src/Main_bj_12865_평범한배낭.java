import java.io.*; 
import java.util.*; 
public class Main_bj_12865_평범한배낭 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[K+1]; 
		int[][] pkg = new int[N+1][2]; // (무게, 가치) 
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			pkg[i][0] = Integer.parseInt(st.nextToken()); 
			pkg[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N+1; i++) {
			int w_i = pkg[i][0]; 
			int v_i = pkg[i][1]; 
			for(int w=K; w>0; w--) {
				if(w_i <= w) {
					dp[w] = Math.max(dp[w], dp[w-w_i]+v_i);
				}
			}
//			System.out.println(Arrays.toString(dp));
		}
		StringBuilder sb = new StringBuilder(); 
		sb.append(dp[K]);
		System.out.println(sb.toString());

	}

}
/*
01 냅색 문제 
N개의 물건 (무게 w, 가치 v) 
최대 무게 K 
int[] dp = new int[K+1]; 
각 물건에 대해 w를 1..K까지 돌리며 넣을 수 있을 때 최대 가치를 계산한다. 
*/