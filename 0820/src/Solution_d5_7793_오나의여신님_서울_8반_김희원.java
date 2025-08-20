import java.io.*; 
import java.util.*; 

public class Solution_d5_7793_오나의여신님_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 악마의 손아귀 확장: 1초에 상하좌우 인접영역으로 퍼짐. 돌이 있는 곳은 못 퍼짐 
		 * 수연이: 1초에 상하좌우 인접칸으로 이동 가능. 돌이 있으면 못 감. 
		 * 목적지는 'D'
		 * 최소시간 이동. (최단경로) 
		 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // NxM 
			mat = new char[N][M]; 
			
			int[] start = new int[2]; 
			for(int i=0; i<N; i++) {
				String s = br.readLine(); 
				for(int j=0; j<M; j++) {
					mat[i][j] = s.charAt(j);
					if(mat[i][j] == 'S') start = new int[] {i, j};
				}
			}
			
			boolean able = true; 
			int time = 0; 
			v = new boolean[N][M]; 
			
			// 시작점을 큐에 넣고, bfs 할 준비 
			q = new ArrayDeque<>(); 
			q.add(new int[] {start[0], start[1]});
			v[start[0]][start[1]] = true; 
			
			while(!q.isEmpty()) {
				time++; 
				// 1초마다 악마의 손아귀 상하좌우로 퍼져나감 
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(mat[i][j] == '*') devil(i, j); 
					}
				}
				
				// 그때 S가 갈 수 있는 곳을 찾는다. 
				int qSize = q.size(); 
				for(int i=0; i<qSize; i++) {
					// 큐의 정점을 뽑고 
					int[] cur = q.poll(); 
					// 그게 목적지이면 빼고 
					if(mat[cur[0]][cur[1]] == 'D') {
						able = true; 
						break; 
					}
					// 아니라면, 주변 4방탐색을 하면서 갈 수 있는 곳을 큐에 모두 넣는다. 
					for(int d=0; d<4; d++) {
						int nx = cur[0] + dx[d]; 
						int ny = cur[1] + dy[d]; 
						// 조건. 1) 범위 2) *이거나 X이면 못감 3) 방문 안한 곳이어야 함 
						if(0<=nx && nx<N && 0<=ny && ny<M) {
							if(v[nx][ny] || mat[nx][ny] == '*' || mat[nx][ny] == 'X') continue; 
							q.add(new int[] {nx, ny});
							v[nx][ny] = true; 
						}
					} 
				}
			}
			System.out.println(time);
			q.clear(); 
		}
	}
	static int N, M; 
	static char[][] mat; 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayDeque<int[]> q; 
	static boolean[][] v; 
	static void devil(int i, int j) {
		for(int d=0; d<4; d++) {
			int ni = i+dx[d], nj = j+dy[d]; 
			if(0<=ni && ni<N && 0<=nj && nj<M) {
				if(mat[i][j] == 'D' || mat[i][j] == 'X') continue; 
				mat[ni][nj] = '*';
			}
		}
	}
}
