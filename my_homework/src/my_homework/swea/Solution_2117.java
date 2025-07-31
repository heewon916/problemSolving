package my_homework.swea;
import java.util.*; 
import java.io.*; 
/**
 * bfs + 3중반복문으로 풀이 시, 
 * O(N * N * 2N * N^2) = O(N^5) ~= 
 */
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
		
		// 첫번째 위치도 방문 처리 잊지 말고 해줘야 함. 
		visited[cx][cy] = true; 
//		System.out.println("k = " + k);
		while(!q.isEmpty()) {
			int[] t = q.poll();
			int tx = t[0], ty = t[1]; 
//			visited[tx][ty] = true; 
			
			if(mat[tx][ty] == 1) res++; 
//			System.out.println("available pos = " + tx + ", " + ty);
			
			for(int d=0; d<4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
//					int dist = Math.abs(nx-t[0]) + Math.abs(ny-t[1]);
					int dist = Math.abs(nx-cx) + Math.abs(ny-cy);
					// 맨해튼 거리 => (dist <= k)가 아니다.
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
//		int T = 1; 
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
			int res_hCount = 0; 
			int cost, houseCount/*, currBenefit=0*/; 
			// 1인 곳에 대해서, K값을 달리하며 1 개수 count 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {	
//					for(int k=1; k <= N/2+1; k++) {
					for(int k=1; k <= 2*N; k++) {
						// k값에 따라 운영비용 계산 후, 수익 계산해서 업데이트
						cost = k*k + (k-1)*(k-1);
						houseCount = bfs(i, j, k); // 서비스 영역 안에 들어갈 집의 개수 
						// 운영비용보다 수익이 크거나 같고, 그 중에서 가장 많은 집들에 제공하는 서비스 영역 
						// currBenefit은 조건이 아니다. 내 맘대로 해석하지 않기
						if(houseCount*M >= cost) {
							res_hCount = Math.max(res_hCount, houseCount); 
						}
//						if((currBenefit < (houseCount*M - cost)) && (res_hCount < houseCount)) {
//							if(res_hCount < houseCount) {
//								currBenefit = houseCount*M - cost; 
//								res_hCount = houseCount; 	
//						}
					}		
				}
			}
			sb.append("#").append(tc).append(" ").append(res_hCount).append("\n");
		}
		System.out.println(sb.toString());

	}

}
