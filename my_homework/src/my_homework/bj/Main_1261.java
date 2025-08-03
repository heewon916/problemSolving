package my_homework.bj;
import java.util.*; 
import java.io.*;

/**
 * 다익스트라 문제 
 */

public class Main_1261 {
	static int N, M; 
	static int[] dx = {-1, 1, 0, 0}; 
	static int[] dy = {0, 0, -1, 1}; 
	static int[][] mat; 

	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 현재 좌표 1,1부터 시작 중 
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mat = new int[M][N]; 
		for(int i=0; i<M; i++) {
			String input = br.readLine();

			for(int j=0; j<N; j++) {
				mat[i][j] = input.charAt(j) - '0';
			}
		}
		
		// 우선순위 큐: [누적비용, x, y] 형태, 비용이 작은 것부터 처리
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); 
		pq.add(new int[] {0, 0, 0}); // 시작점 (비용0, 0,0)
		
		// visited를 하지 않는 이유: 이미 전에 도달했어도 나중에 더 적은 비용으로 올 수 있다면 다시 방문하는 게 맞다.
		// boolean[][] v = new boolean[N][M]; 
		int[][] arr = new int[M][N]; // 각 칸에 도달할 때 최소 벽 부순 횟수 => 누적비용 담는 곳; 
		for(int[] row: arr) Arrays.fill(row, Integer.MAX_VALUE);

		// 다익스트라 탐색 시작
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int sumCost = curr[0], x = curr[1], y = curr[2]; 
			//v[x][y] = true; 
			// System.out.printf("[%d, %d] => 현재까지 누적비용 = %d\n", x, y, sumCost);
			for(int d=0; d<4; d++) {
				int nx = x + dx[d] , ny = y + dy[d]; 
				if(0<=nx && nx<M && 0<=ny && ny<N /*&& !v[nx][ny]*/) {
					int newCost = sumCost + mat[nx][ny]; // 벽이면 +1, 빈 방이면 +0
					// 더 적은 비용으로 (nx,ny)에 도달할 수 있을 때만 갱신
					if(newCost < arr[nx][ny]) {
//						System.out.printf("goto(%d, %d) -> newCost = %d\n", nx, ny, newCost);
						arr[nx][ny] = newCost;
						pq.add(new int[] {newCost, nx, ny});
					}
				}
			}
//			for(int[] elem: pq) System.out.println("@"+Arrays.toString(elem));
//			System.out.println();
		}
		if(N==1 && M==1) {
			System.out.println(0);
		}else {
		System.out.println(arr[M-1][N-1]);
	
		}
	}
}
/**
 * 예외 케이스: 1. 출발점 == 도착점 2. 도착지 갈 수 없음 3. 벽이 없음. 4. 모든 칸이 벽. 5. 길이 나 있는 경우 
 */
