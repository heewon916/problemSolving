import java.io.*;
import java.util.*; 

public class Solution_D5_1247_최적경로_서울_8반_김희원 {

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
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			List<int[]> homes = new LinkedList<>(); 
			boolean[] visited = new boolean[N];
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			System.out.println("초기화: sx = " + sx + " sy = " + sy + " tx = " + tx + " ty =" + ty );
			for(int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				homes.add(new int[] {x, y});
			}
//			homes.add(new int[] {tx, ty});
			for(int[] a : homes) System.out.print(Arrays.toString(a) + " "); System.out.println();
			// 시작점에 대해서 
			// 최소 거리의 집을 찾아서  
			// 그걸 시작점에 대해서 다시 반복하기 
			// 언제 끝나냐면 sx, sy가 tx, ty가 되면 
			int answer = -1; 
			while(true) {
				if(sx == tx && sy == ty) {
					System.out.printf("목적지 도착: sx=%d, sy=%d\n", sx, sy);
					break;
				}
				int min_dist = Integer.MAX_VALUE; 
				int min_x = -1, min_y = -1; 
				// sx, sy에 대해서 
				// 방문하지 않았고 맨허튼 거리가 최소인 집을 찾아야 함 
				// sx, sy에 대해 거리를 계산하고 가장 가까운 지점을 찾으면 됨 
				// 가장 가까운 지점은 sx, sy가 다시 됨 
				// homes에 내 집까지 넣었더니 문제 발생함 
				// 회사에서 내 집이 가장 가까운 경우가 되는 것임 
				int idx = 0, min_idx = -1; 
				for(int[] cur: homes) {
					int cx = cur[0], cy = cur[1];
					if(cx == tx && cy == ty) continue; 
					int dist = Math.abs(cx - sx) + Math.abs(cy - sy);
					if((min_dist > dist) && !visited[idx]) {
						min_dist = dist; 
						min_x = cx; min_y = cy; 
						min_idx = idx; 
					}
					idx++; 
				}
				if(min_idx == -1) break;
				sx = min_x; sy = min_y; 
				visited[min_idx] = true; 	
				answer += min_dist; 
				System.out.printf("sx=%d, sy=%d -> min_dist=%d/ answer=%d ", sx, sy, min_dist, answer);
				System.out.println(Arrays.toString(visited));
			}
			
			System.out.println(answer + Math.abs(sx-tx) + Math.abs(sy-ty));
//0 0 
//100 100 
//70 40 
//30 10 
//10 5
//90 70 
//50 20 
//
//0 0 - 10 5 -> 15
//10 5 - 30 10 -> 20 + 5 = 25 
//30 10 - 
		}
	}
}
