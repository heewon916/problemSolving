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
			visited = new boolean[N][M]; 
			devTime = new int[N][M];
			ArrayDeque<int[]> devQ = new ArrayDeque<>(); 
			ArrayDeque<int[]> sQ = new ArrayDeque<>(); 
			
			int sx = -1, sy = -1; 
			for(int i=0; i<N; i++) {
				String s = br.readLine(); 
				for(int j=0; j<M; j++) {
					mat[i][j] = s.charAt(j);
					devTime[i][j] = Integer.MAX_VALUE; 
					if(mat[i][j] == 'S') {
						sx = i; sy = j; 
					} else if (mat[i][j] == '*') {
						devTime[i][j] = 0; 
						devQ.offer(new int[] {i, j});
					}
				}
			}
			
			// 악마가 확산될 때, 각 좌표는 어느 time에 확산되는지 미리 계산한다. 
			while(!devQ.isEmpty()) {
				int[] cur = devQ.poll(); 
				for(int d=0; d<4; d++) {
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d]; 
					if(0<=nx && nx<N && 0<=ny && ny<M) {
						if(mat[nx][ny] == '.' && devTime[nx][ny] == Integer.MAX_VALUE) {
							devTime[nx][ny] = devTime[cur[0]][cur[1]] + 1; 
							devQ.offer(new int[] {nx, ny});
						}
					}
				}
			}
			
			// 위에서 구한 악마가 확산되는 time에 맞게
			// S는 현재 갈 수 있는 곳 중에서 time 시점에 *가 아닌 곳으로 가면 된다. 
			
			sQ.offer(new int[] {sx, sy, 0});
			visited[sx][sy] = true; 
			int answer = -1; 
			
			while(!sQ.isEmpty()) {
				int[] cur = sQ.poll(); 
				int x = cur[0], y = cur[1], t = cur[2];
				
				if(devTime[x][y] <= t && mat[x][y] != 'S') continue; 
				
				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d]; 
					int nt = t+1; 
					if(0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny]) {
						if(mat[nx][ny] == 'D') {
							answer = nt; 
							sQ.clear(); 
							break; 
						}
						if(mat[nx][ny] == '.' && devTime[nx][ny] > nt) {
							visited[nx][ny] = true; 
							sQ.offer(new int[] {nx, ny, nt});
						}
					}
				}
				
			}
			sb.append("#").append(tc).append(" ").append(answer == -1 ? "GAME OVER": answer).append("\n");				
		}
		System.out.println(sb.toString());
	}
	static int N, M; 
	static char[][] mat; 
	static int[][] devTime; 
	static boolean[][] visited; 
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

}
