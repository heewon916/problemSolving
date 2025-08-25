import java.io.*;
import java.util.*; 

public class Solution_d5_1247_최적경로_서울_8반_김희원 {

	public static void main(String[] args) throws Exception{
		// 회사에서 n명의 고객 모두 방문하고 집으로 돌아감 
		// 두 위치 사이 거리 = 맨허튼 거리 
		// 회사 좌표 집 좌표 고객좌표는 모두 다르다 
		// 회사에서 고객 모두 방문하고 집 돌아가는 경로 중에서 가장 짧은 거 찾기 
		// 가중치가 모두 같은 그래프 
		// 모든 가능한 경로를 살펴서 체계적으로 따져라 
		
		
		// 고객의 수 2~10 
		// n+2개의 좌표 서로 다른 위치 
		// 
		
		// 테스트 개수 t
		// 고객의 수 n 
		// 회사 좌표, 집 좌표, n명의 고객 좌표 나열 공백 구분 
		
		// (sx, sy) <- 회사 좌표 
		// (tx, ty) <- 집 좌표 
		// 100 x 100 int[][] 배열 
		// 고객 집의 좌표에 1입력 
		// 10^4 * 4바이트 = 메모리 공간 충분 
		// bfs, dfs는 인접영역을 찾아다니는 건데 이 경우는 집들의 좌표가 서로 인접하지 않을수도 있다. 
		// N개의 고객 집들의 좌표를 순열로 순서를 구해서, 각 지점 간의 거리를 모두 더해서 
		// 그때의 최소 거리를 그때마다 업데이트하는 걸로? 
		// 순열은 최대 10! -> 쉽지 않을 것 같은데 
		// 회사를 시작점으로 해서 고객 집들을 큐에 넣어두고, N번을 돌려가며 가장 가까운 지점을 찾고, 
		// 그 지점을 다음 시작점으로 지정해서 그 지점과 가장 가까운 고객집을 찾는다. 
		// 이걸 반복하면 최소 거리이지 않을까../ 
		// 시간 복잡도는 각 고객 집마다 N-k씩 => 결국 순열처럼 돌아간다. 
		// 3,628,800 << 2,000,000,000
		// 순열로 그냥 가보죠 
		System.setIn(new FileInputStream("C:\\SSAFY\\homework\\0825\\Input1247.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			answer = Integer.MAX_VALUE; 
			N = Integer.parseInt(st.nextToken());
			homes = new ArrayList<>(); 
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				homes.add(new int[] {x, y});
			}
			b = new int[N+2][2]; 
			b[0] = new int[]{sx, sy}; 
			b[N+1] = new int[]{tx, ty}; 
			perm(1, 0); 
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static int N; 
	static int[][] b; 
	static boolean[] visited; 
	static List<int[]> homes;
	static int answer = Integer.MAX_VALUE;
	static void perm(int cnt, int sum) {
		if(sum >= answer) return; 
		if(cnt == N+1) {
//			int min_dist = 0; 
//			for(int i=0; i<N+1; i++) {
//				int dist = Math.abs(b[i][0] - b[i+1][0]) + Math.abs(b[i][1] - b[i+1][1]);
//				min_dist += dist; 
//			}
//			answer = Math.min(answer, min_dist);
			sum += Math.abs(b[N][0] - b[N+1][0]) + Math.abs(b[N][1] - b[N+1][1]);
			if(sum < answer) answer = sum; 
			return;  
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue; 
			b[cnt] = homes.get(i);
			visited[i] = true; 
			perm(cnt+1, sum + Math.abs(b[cnt-1][0] - b[cnt][0]) + Math.abs(b[cnt-1][1] - b[cnt][1]));
			visited[i] = false; 
		}
	}
}
