import java.io.*; 
import java.util.*; 
public class Solution_d4_7733_치즈도둑_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		/*
		 * x번째 날에는 x번호가 적인 칸이 방문된다 
		 * 먹고 난 뒤에, 치즈 덩어리가 몇 개 있는지 세본다. = 네트워크 개수 세기 
		 * N 10^2 
		 * 제한 시간 2초 
		 * 
		 * 먹게 하고? bfs하면 되지 않을까 
		 */
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0820\\Test7733.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			mat = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max_Networks = 0; 
			for(int day=0; day <= 100; day++) {
				// day날 day 칸 먹음 
//				for(int i=0; i<N; i++) {
//					for(int j=0; j<N; j++) {
//						if(mat[i][j] == day) mat[i][j] = -1; 
//					}
//				}
				// 치즈 덩어리 찾으러 감; 상하좌우로 인접한 칸들
				// 그날 그날 bfs 새롭게 해주기 
				int network_cnt = 0; 
				boolean[][] visited = new boolean[N][N];
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(!visited[i][j] && mat[i][j] > day) {
							network_cnt++; 
							bfs(i, j, day, visited);
						}
					}
				}
				max_Networks = Math.max(max_Networks, network_cnt);
			}
			sb.append("#").append(tc).append(" ").append(max_Networks).append("\n"); 
		}
		System.out.println(sb.toString());

	}
	static int[][] mat; 
	static int[] dx = {-1, 1, 0, 0}; 
	static int[] dy = {0, 0, -1, 1}; 
	static int N; 
	static void bfs(int i, int j, int day, boolean[][] visited) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		q.add(new int[] {i, j});
		visited[i][j] = true;  
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			for(int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny] && mat[nx][ny] > day) {
					// 먹지 않은 인접한 칸들에 대해서 모두 방문 처리 
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true; 
				}
			}
		}
	}

}
