/*
 * r x c 크기 의 표 
 * 지뢰가 없는 칸 0이면 인접한 8칸에 대해 지뢰 몇 개 있는지 알려줌 
 * - 근데 이 인접한 8칸 중에서 0이 있으면 그 칸의 인접한 8칸도 자동으로 숫자 표시해줌 
 * 있으면 "파핑파핑" 소리 내면서 멈춤 
 * 
 * 지뢰는 *이고, 없는 칸은 .임 
 * 지뢰가 있는 칸 제외하고, 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번 클릭해야 하니 
 * 
 * 미리 다 세는 게 낫지 않을까 
 * - 지뢰가 아닌 내 주변 8칸에 지뢰 개수 세서 표시하면 됨 
 * 
 * 내 주위 인접 8칸에 0이 많은 곳을 먼저 찾으면 되지 않나? 
 * 가장 많이 퍼지는 곳?
 * 
 */
import java.io.*; 
import java.util.*; 
public class Solution_d4_1868_파핑파핑지뢰찾기_서울_8반_김희원 {
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0}; 
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1}; 
	static char[][] mat;
	static int[][] bombCntMatrix;
	static int N; 
	static boolean[][] visited; 
	
	static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>(); 
		visited[i][j] = true; 
		q.add(new int[] {i, j});
		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int ci = cur[0], cj = cur[1]; 
			for(int d=0; d<8; d++) {
				int ni = ci+dx[d], nj = cj+dy[d]; 
				if(0<=ni && ni<N && 0<=nj && nj<N) {
					if(!visited[ni][nj] && mat[ni][nj] != '*') {
						visited[ni][nj] = true; 
						if(bombCntMatrix[ni][nj] == 0) q.add(new int[] {ni, nj});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0825\\Input1868.txt"));
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			// step1. 입력 처리하기 
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			mat = new char[N][N]; 
			for(int i=0; i<N; i++) {
				String s = br.readLine(); 
				for(int j=0; j<N; j++) {
					mat[i][j] = s.charAt(j);
				}
			}
			
			// step2. NxN 배열에 대해서 지뢰가 없는 칸 각각에 대해, 인접 8칸에 있는 지뢰 개수 저장 
			bombCntMatrix = new int[N][N]; 
			
			// step2-1. '.'인 좌표들에 대해서, 주변 인근 8칸 확인하기 => 지뢰가 있으면 bomb_count++; 
			// step2-2. '*'인 좌표는 -1로 처리. 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mat[i][j] == '.') {
						int bomb_count = 0; 
						for(int d=0; d<8; d++) {
							int ni = i+dx[d], nj = j+dy[d]; 
							if(0<=ni && ni<N && 0<=nj && nj<N) {
								if(mat[ni][nj] == '*') bomb_count++; 
							}
						}
						bombCntMatrix[i][j] = bomb_count; 
					}else {
						bombCntMatrix[i][j] = -1; 
					}
				}
			}
			
			// step3. bombCntMatrix에서 0인 곳들만 일단 살펴보자. 
			//=> 결과) 0으로 인해 연쇄적으로 방문할 수 있는 곳들은 모두 방문된다. 
			//=> 연쇄방문이므로, if문 한 번에 1번만 클릭 카운트. 
			int answer = 0; 
			visited = new boolean[N][N]; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(bombCntMatrix[i][j] == 0 && !visited[i][j]) {
						// i, j 방문 처리하고 
						// i, j의 인접 8칸을 모두 방문처리하고 
						// 그 중에서 0인 곳이 있으면 또 bfs(ni, nj);
						answer++; 
						bfs(i, j); 
					}
				}
			}
			
			// step4. 방문 못 했고, 지뢰가 아닌 좌표는 각각 클릭해줘야 하므로 answer++; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 지뢰가 있거나, 이미 방문한 곳이면 넘기고 
					if(mat[i][j] == '*' || visited[i][j]) continue; 
					// 방문 못한 곳들의 개수를 센다. 
					answer++;
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n"); 
		}
		System.out.println(sb.toString());
	}

}
