import java.io.*; 
import java.util.*; 

/*
 * 엄청 헤맨 이유: 
 * 0행에서 출발지를 모두 찾고, 그 출발지들마다 각각 갈 수 있는 길을 하좌우 3방향으로 고려해 dfs로 구현해보려 했다. 
 * 그런데 갈수록 코드는 무지막지하게 복잡해짐. 
 * 게다가 범위 체크하는데 애도 먹음;; 
 * 
 * 짝꿍이 귀띔 해주기로는 어차피 사다리에는 정답루트가 1개니까 3방향 탐색할 필요가 없다는 점이다. 
 * 따라서, 도착점을 미리 찾고, 도착점에서 역탐색하는 게 더 효율적이라고 할 때, 
 * 탐색이 어려울 때는 거꾸로 찾아올라가야 한다는 아이디어가 있었음이 기억났다.. 
 * 
 * 그래서 도착점을 mat에 값 넣을 때 찾아두고, 
 * 
 * d = 1,2 즉 좌우 먼저 탐색해본다. 
 * 좌우에서 갈 수 있는 방향이 있다면 그 방향으로 쭈우욱-- 가본다. (즉, mat[i][j] != 1일때까지) 
 * 
 * 더이상 갈 수가 없어지면 무조건 위로 한 칸 올라가도록 한다. 
 * 
 * 이때 올라갈 수 있는 길이 없었더라도 무조건 위로 한 칸 올린다. 
 * 
 * 이 문제는 완벽한 구현 + 시뮬레이션 문제가 아닐까 싶다;; 
 * */

public class Solution_d4_1210_Ladder1_서울_8반_김희원 {
	static int[][] mat = new int[100][100];
	static int[] dx = {-1, 0, 0};
	static int[] dy = {0, -1, 1}; // 상좌우 

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("C:/SSAFY/homework/0806/Test1210.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=0; t<10; t++) {
			st = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(st.nextToken());
//			int answer = -1; // 출발점 찾기 
//			int[] endPos; 
//			List<Integer> startPos = new ArrayList<Integer>(); 
			
			int x = -1, y = -1; 
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<100; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
					if(mat[i][j] == 2) {
						x = i; y = j; 
					}
				}
			}
//			for(int i=0; i<100; i++) {
//				if(mat[0][i] == 1) startPos.add(i);
//			}
			// end좌표부터 거슬러올라가기
			while(x > 0) {
				boolean moved = true; 
				for(int d=1; d<=2; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					// 좌나 우로 갈 수 있다면 
					if(0<=ny && ny<99 && mat[nx][ny] == 1) {
						while(0<=ny && ny<100 && mat[nx][ny] == 1) {
							y = ny;  // 이게 정답이 된다. 
							ny = ny + dy[d]; 
						}
						moved = true;
						break; 
					}
				}
				if(!moved) x += dx[0]; // 좌나 우로 못 갔으면 위로 한 칸 
				else x += dx[0]; // 이동했어도 위로 한 칸 올라가기 
			}
			sb.append("#").append(tc).append(" ").append(y).append("\n");
			System.out.println();
		}
		System.out.println(sb.toString());
	}
	/* 삽질의 흔적... 
	static boolean bfs(int i) { // 
		ArrayDeque<int[]> q = new ArrayDeque<>(); 
		boolean[][] v = new boolean[100][100];
		q.add(new int[] {0, i});

		while(!q.isEmpty()) {
			int[] cur = q.poll(); 
			int x = cur[0], y = cur[1]; 
			v[x][y] = true; 
			System.out.println("x = " + x + " y =" + y);
			if(mat[x][y] == 2) return true; 
			
			for(int d=0; d<3; d++) {
				int nx = x+dx[d], ny = y+dy[d]; 
				if(d == 0 || d == 1) {
					if(0<=nx && nx<100 && 0<=ny && ny<100 && mat[nx][ny] == 1 && !v[nx][ny]) {
						q.add(new int[] {nx, ny});
						v[nx][ny] = true; 
					}
					
				} else {
					if(0<=nx && nx<100 && 0<=ny && ny<100 && mat[nx][ny] == 1 && !v[nx][ny]) {
						q.add(new int[] {nx, ny});
						v[nx][ny] = true; 
					}
				}	
			}
		}
		return false; 
	}
	static boolean dfs(int i, int j, boolean[][] v) {
		v[i][j] = true; 
		if(mat[i][j] == 2) return true; 
		
		if(j>0 && mat[i][j-1] == 1 && !v[i][j-1]) { // 왼쪽으로 길이 있으면 
			int nj = j; 
			while(nj>0 && mat[i][nj-1] == 1 && !v[i][nj-1]) { // 왼쪽으로 쭉 
				nj--; 
				v[i][nj] = true; 
			}
			if(dfs(i, nj, v)) return true; 
		} else if(j<99 && mat[i][j+1] == 1 && !v[i][j+1]) {
			int nj = j; 
			while(nj<99 && mat[i][nj+1] == 1 && !v[i][nj+1]) {
				nj++; 
				v[i][nj] = true; 
			}
			if(dfs(i, nj, v)) return true; 
		} else if(i<99 && mat[i+1][j] == 1 && !v[i+1][j]){
			if(dfs(i+1, j, v)) return true; 
		}
		return false; 
	}
	*/
}

