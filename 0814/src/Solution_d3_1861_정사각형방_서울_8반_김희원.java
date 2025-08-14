import java.io.*;
import java.util.*; 

public class Solution_d3_1861_정사각형방_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0814/Test1861.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][N]; 
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 처음에 출발해야 하는 방 번호
			// 최대 몇 개의 방을 이동할 수 있는지를 공백으로 구분하여 출력
			int cnt;
			int res_cnt = Integer.MIN_VALUE; 
			int res_val = Integer.MAX_VALUE; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					cnt = dfs(mat, i, j, N);
					if(res_cnt < cnt) {
						res_cnt = cnt; 
						res_val = mat[i][j]; 
					} else if(cnt == res_cnt && mat[i][j] < res_val) {
						res_val = mat[i][j]; 
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(res_val).append(" ").append(res_cnt).append("\n");
			
			res_cnt = Integer.MIN_VALUE; 
			res_val = Integer.MAX_VALUE; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					cnt = bfs(mat, i, j, N);
					if(cnt > res_cnt){
						res_val = mat[i][j]; 
						res_cnt = cnt;
					} else if (cnt == res_cnt && res_val > mat[i][j]) {
						res_val = mat[i][j]; 
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(res_val).append(" ").append(res_cnt).append("\n");

			res_cnt = Integer.MIN_VALUE; 
			res_val = Integer.MAX_VALUE; 
			int[][] dp = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					cnt = dfsDp(mat, dp, i, j, N);
					if(cnt > res_cnt){
						res_val = mat[i][j]; 
						res_cnt = cnt;
					} else if (cnt == res_cnt && res_val > mat[i][j]) {
						res_val = mat[i][j]; 
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(res_val).append(" ").append(res_cnt).append("\n");

		}
		System.out.println(sb.toString());

	}
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static int  dfsDp(int[][] mat, int[][] dp, int sx, int sy,int N) {
		if(dp[sx][sy] != 0) return dp[sx][sy]; 
		
		dp[sx][sy] = 1; 
		for(int d=0; d<4; d++) {
			int nx = sx+dx[d], ny = sy+dy[d]; 
			if(0<=nx && nx<N && 0<=ny && ny<N) {
				if(mat[sx][sy]+1 == mat[nx][ny]) {
					dp[sx][sy] = 1 + dfsDp(mat, dp, nx, ny, N);
					break; 
				}
			}
		} 
		return dp[sx][sy]; 
	}
	static int dfs(int[][] mat, int sx, int sy, int N) {
		int cnt = 1; 
		for(int d=0; d<4; d++) {
			int nx = sx+dx[d], ny = sy+dy[d]; 
			if(0<=nx && nx<N && 0<=ny && ny<N) {
				if(mat[sx][sy]+1 == mat[nx][ny]) {
					cnt = Math.max(cnt, 1+dfs(mat, nx, ny, N));
				}
			}
		}
		return cnt; 
	}
	static int bfs(int[][] mat, int sx, int sy, int N) {
		int cnt = 1; 
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		q.add(new int[] {sx, sy});
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int cx = cur[0], cy = cur[1];
			for(int d=0; d<4; d++) {
				int nx = cx+dx[d], ny = cy+dy[d]; 
				if(0<=nx && nx<N && 0<=ny && ny<N) {
					if(mat[cx][cy]+1 == mat[nx][ny]) {
						cnt++; 
						q.add(new int[] {nx, ny});
						break; 
					}
				}
			}
		}
		return cnt; 
	}
}
/*
2
2
1 2
3 4
3
9 3 4
6 1 5
7 8 2
*/
