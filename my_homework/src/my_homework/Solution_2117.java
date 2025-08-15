package my_homework;
import java.util.*; 
import java.io.*; 

public class Solution_2117 {
	static int[][] mat; 
	static int N; 
	static int M; 
//	static int res = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int bfs(int cx, int cy, int k) { // -> 서비스 영역 내 집의 개수 리턴 
		int res = 0; 
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<int[]> q  = new ArrayDeque<>(); 
		q.add(new int[]{cx, cy});
		while(!q.isEmpty()) {
			int[] t = q.poll();
			int tx = t[0], ty = t[1]; 
//			visited[tx][ty] = true; 
			
			if(mat[tx][ty] == 1) res++; 
			
			for(int d=0; d<4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
//					int dist = Math.abs(nx-t[0]) + Math.abs(ny-t[1]);
					int dist = Math.abs(nx-cx) + Math.abs(ny-cy);
					if(dist < k) {
						visited[nx][ny] = true; 
						q.add(new int[]{nx, ny});
						// res++; 
					}
				}
			}
		}
		return res; 
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("Test2117.txt"));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			mat = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int res = 0; 
			int cost, houseCount, currBenefit=0; 
			// 1인 곳에 대해서, K값을 달리하며 1 개수 count 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {	
//					for(int k=1; k <= N/2+1; k++) {
					for(int k=1; k <= 2*N; k++) {
						// k값에 따라 운영비용 계산 후, 수익 계산해서 업데이트
						cost = k*k + (k-1)*(k-1);
						houseCount = bfs(i, j, k); // 서비스 영역 안에 들어갈 집의 개수 
						if(currBenefit < houseCount*M - cost && res <= houseCount) {
							currBenefit = houseCount*M - cost; 
							res = houseCount; 
						}
					}		
				}
			}
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb.toString());

	}

}
